/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package congcv.util;

import java.io.Serializable;

/**
 *
 * @author Kieu Trong Khanh
 */
public class MyAppConstants implements Serializable {
    
    public static final String ERROR_PAGE = "errorPage";
    
    public class LoginFeatures {
        public static final String INVALID_PAGE = "invalid";
        public static final String LOGIN_FAIL_PAGE = "loginFailPage";
        public static final String LOGIN_PAGE = "loginPage";
        public static final String SEARCH_ACTION = "searchAction";
    }
    
    public class SearchAccountFeatures {
        public static final String SEARCH_PAGE = "searchPage";
        public static final String SEARCH_CONTROLLER = "searchAction";
    }
    
    public class BookStoreFeatures {
        public static final String BOOK_STORE_PAGE = "storePage";
        public static final String BOOK_STORE_CONTROLLER = "storeView";
        public static final String CART_STORE_PAGE = "cartPage";
        public static final String ADD_ITEM_TO_CART_CONTROLLER = "cartAddItem";
        public static final String REMOVE_ITEM_CONTROLLER = "cartDeleteItemAction";
        public static final String CHECKOUT_BOOK_STORE_CONTROLLER = "cartCheckOutAction";
        public static final String CHECKOUT_BOOK_STORE_PAGE = "cartCheckOutPage";
    }
    
    public class CreateNewAccountFeatures {
        public static final String CREATE_NEW_ACCOUNTS_PAGEH = "registerPageh";
        public static final String CREATE_NEW_ACCOUNTS_PAGEJ= "registerPagej";
        public static final String CREATE_NEW_ACCOUNTS_CONTROLLER = "registerAction";
        
    }
    
    
    
}
