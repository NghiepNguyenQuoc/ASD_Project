package com.myhotel.view;

import java.util.ResourceBundle;

public enum FxmlView {

    USER {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("user.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/User.fxml";
        }
    }, 
    LOGIN {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Login.fxml";
        }
    },
    REGISTER {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("register.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Register.fxml";
        }
    },
    USERINFO{
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("userinfo.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/UserInfo.fxml";
        }
    },
	BOOKING {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("booking.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Booking.fxml";
        }
    },
	VIEWROOMS {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("viewrooms.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/ViewRooms.fxml";
        }
    },
	ADDCARD {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("addcard.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/AddCard.fxml";
        }
    },
	REMOVECARD {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("removecard.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/RemoveCard.fxml";
        }
    };
    
    public abstract String getTitle();
    public abstract String getFxmlFile();
    
    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
