package fr.polytech.ig5.CSALUsers;

public class RightChecker {
    public static boolean CheckRight(Action action, Role role){
        switch (role){
            case ROLE_ADMIN : switch (action){
                default : return true;
            }
            case ROLE_EMPLOYER : switch (action) {

                case READ_USER:return true;
                case READ_OFFER:return true;
                case READ_AFFECT:return true;
                case CREATE_USER:return true;
                case DELETE_USER:return true;
                case UPDATE_USER:return true;
                case CREATE_OFFER:return true;
                case DELETE_OFFER:return true;
                case READ_KEYWORD:return true;
                case UPDATE_OFFER:return true;
                case CREATE_AFFECT:return true;
                case DELETE_AFFECT:return true;
                case UPDATE_AFFECT:return true;
                case READ_Advantage:return true;

                default : return false;
            }
            case ROLE_SEEKER : switch ((action)){

                case READ_USER:return true;
                case READ_OFFER:return true;
                case READ_AFFECT:return true;
                case CREATE_USER:return true;
                case DELETE_USER:return true;
                case UPDATE_USER:return true;
                case READ_KEYWORD:return true;
                case UPDATE_OFFER:return true;
                case UPDATE_AFFECT:return true;
                case READ_Advantage:return true;

                default : return  false;
            }
        }



        return false;
    }
}
