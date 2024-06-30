package pageUIs.jqueryscript;

public class HomePageUI {

    public static final String DYNAMIC_PAGINATION_BUTTON = "xpath=//li[@class='qgrd-pagination-page']/a[text()='%s']";

    public static final String DYNAMIC_SEARCH_TEXTBOX = "xpath=//div[text()='%s']/parent::div/following-sibling::input";

    public static final String ALL_SEARCH_TEXTBOXES = "css=input.qgrd-header-filter";

    public static final String DYNAMIC_DATA_ROW = "xpath=//td[@data-key='females' and text()='%s']" +
            "//following-sibling::td[@data-key='country' and text()='%s']" +
            "//following-sibling::td[@data-key='males' and text()='%s']" +
            "//following-sibling::td[@data-key='total' and text()='%s']";

    public static final String DYNAMIC_ACTION_BUTTON = "xpath=//td[@data-key='%s' and text()='%s']" +
            "/preceding-sibling::td[@class='qgrd-actions']/button[contains(@class,'%s')]";

    public static final String SUBMIT_BUTTON = "xpath=//form//input[@type='submit']";

    public static final String DYNAMIC_ALL_CELLS_OF_COLUMN = "xpath=//tbody//td[@data-key='%s']";

    public static final String DYNAMIC_ALL_CELLS_OF_ROW = "xpath=//tbody/tr[%s]/td[@data-key]";

//  --------------------------------------------------------------------------------------------------------------------

    public static final String LOAD_DATA_BUTTON = "css=button#load";

    public static final String DYNAMIC_PRECEDING_SIBLING_OF_COLUMN = "xpath=//thead//th[text()='%s']/preceding-sibling::th";

    public static final String DYNAMIC_CELL_TEXTBOX = "xpath=//tr[%s]/td[%s]/input";
//    public static final String DYNAMIC_CELL_TEXTBOX_2 = "xpath=//tr[%s]//input[contains(@id,'%s')]"; --> not recommended

    public static final String DYNAMIC_CELL_DROPDOWN = "xpath=//tr[%s]//select";

    public static final String DYNAMIC_CELL_CHECKBOX = "xpath=//tr[%s]//input[@type='checkbox']";

    public static final String DYNAMIC_CELL_DATE_PICKER = "xpath=//tr[%s]//input[contains(@id,'memberSince')]";

    public static final String DYNAMIC_CELL_ACTION_BUTTON = "xpath=//tr[%s]//button[starts-with(@title,'%s')]";

}
