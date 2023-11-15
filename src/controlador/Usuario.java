package controlador;
    public class Usuario {
        private long userID;
        private String userMail;
        private String userPass;
        //private boolean esAdmin;

        // Constructor
        public Usuario(long u, String m, String p) {
            this.userID = u;
            this.userMail = m;
            this.userPass = p;
        }
        public Usuario(){}

        // Getters y setters

        public long getUserID() {
            return userID;
        }

        public void setUserID(long userUser) {
            this.userID = userUser;
        }

        public String getUserMail() {
            return userMail;
        }

        public void setUserMail(String userMail) {
            this.userMail = userMail;
        }

        public String getUserPass() {
            return userPass;
        }

        public void setUserPass(String userPass) {
            this.userPass = userPass;
        }

        // Metodo toString

        @Override
        public String toString() {
            return "Usuario{" +
                    "userUser='" + userID + '\'' +
                    ", userMail='" + userMail + '\'' +
                    ", userPass='" + userPass + '\'' +
                    '}';
        }
    }
