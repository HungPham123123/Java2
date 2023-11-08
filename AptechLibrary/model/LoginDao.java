    package AptechLibrary.model;


    import AptechLibrary.entity.staff;
    import AptechLibrary.entity.users;

    public interface LoginDao {

        public String checkUserLogin(users user);
        public String checkStaffLogin(staff staff);


    }
