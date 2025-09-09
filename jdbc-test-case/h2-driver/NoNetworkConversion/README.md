指定sql文件，自动生成生成不出网的h2 jdbc poc，原理：先用不出网的poc读取sql文件并写入指定的远程环境路径，然后用runscript进行连接，用法：
```
python NoNetworkConversion.py <本地sql文件路径> <远程环境写入sql文件路径>
```

如：

```
python NoNetworkConversion.py "./poc.sql" "D:/test/aaa"
```

实际案例可参考：[h2 jdbc不出网poc自动生成](./h2 jdbc不出网poc自动生成.pdf)