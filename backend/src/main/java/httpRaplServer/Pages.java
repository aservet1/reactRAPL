package httpRaplServer;

/** Strings that can be sent as basic 'html files' so I don't have to store their content as a real file. */
class Pages {
    public static final String NOT_FOUND = "<html style='text-align:center'><h1>404 not found</h1></html>";
    public static final String WELCOME = "<html style='text-align:center' >\n"+
                                            "<h1> Welcome to the httpRAPL web server.</h1>\n" +
                                            "Make your requests by typing in the appropriate URL. This is just a backend\n" +
                                            "so I'm not incorporating a whole user interface for it.\n"+
                                         "</html>";
}
