package co.com.lulobank.apirest.util;

public enum Resources {
    END_POINT("https://dummy.restapiexample.com"),
    RESOURCE_SEARCH("/api/v1/employees"),
    RESOURCE_SEARCH_BY("/api/v1/employee/"),
    RESOURCE_CREATE("/api/v1/create"),
    RESOURCE_DELETE("/api/v1/delete/");

    private String value;

    Resources(String value) {
        this.value=value;
    }

    @Override
    public String toString(){
        return value;
    }
}
