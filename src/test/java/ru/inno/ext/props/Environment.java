package ru.inno.ext.props;

public enum Environment {
    TEST("test.properties"),
    PROD("prod.properties"),
    SMOKE("smoke.properties");

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    Environment(String fileName) {
        this.fileName = fileName;
    }
}
