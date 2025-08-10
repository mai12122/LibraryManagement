package minterface;

import user.users;

public interface Authentication {

    public abstract void register();
    
    public static boolean login(users account)
    {
        for (users acc : users.accountList) {
            if (account.equals(acc)) {
                return true;
            }
            
        }
        return false;
    }
}