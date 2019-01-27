

import java.io.*;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author <a href="mailto:surya@pramati.com">Surya Chaitanya Tamada</a>
 * @since 20 Jun, 2008
 */
public class LoginAccount implements Serializable
{
    protected Id id;

    protected String userId;
    protected String emailAddress;
    protected String firstName;
    protected String lastName;
    protected String displayName;

    protected boolean confirmed;
   private int networkDomain;
    private Map<String, String> customFields;
    
    protected long lastSeen;
    private long trialEndTime;
    private String userName;
    private String guid;
    private long createTime;

    private static final Logger logger = Logger.getLogger(LoginAccount.class.getName());

   
    public LoginAccount(String accountId, String userId, String userName, String emailAddress, String guid,
                        String firstName,
                        String lastName, String displayName, String corporateId, boolean confirmed,
                         int networkDomain,
                        long trialEndTime)
    {
        id = new Id(accountId, corporateId);
        this.guid = guid;
        this.emailAddress = emailAddress;
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;

        this.confirmed = confirmed;
       

        this.networkDomain = networkDomain;
        this.trialEndTime = trialEndTime;
        this.customFields = new HashMap<String, String>();
        
    }

    public LoginAccount(String accountId, String userId, String userName, String emailAddress, String guid,
                        String firstName,
                        String lastName, String displayName, String corporateId, boolean confirmed,
                         int networkDomain,
                        long trialEndTime, long createTime)
    {
        this(accountId, userId, userName, emailAddress, guid, firstName, lastName, displayName, corporateId, confirmed, 
             networkDomain, trialEndTime);
        this.createTime = createTime;
    }

    public Id getId()
    {
        return id;
    }

    public String getAccountId()
    {
        return id.getAccountId();
    }

    public String getCorporateId()
    {
        return id.getCorporateId();
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getName()
    {
        return getUserId();
    }

    public String getUserId()
    {
        return userId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public boolean isConfirmed()
    {
        return confirmed;
    }

   
    /**
     * @return guid of the oxygen account for this user, or NULL if user does not yet have oxygen account
     */
    public String getGuid()
    {
        return guid;
    }

    

    public long getLastSeen()
    {
        return lastSeen;
    }

    public void setLastSeen(long lastSeen)
    {
        this.lastSeen = lastSeen;
    }

   

   
    public Map<String, String> getCustomFields()
    {
        return customFields;
    }

    public String addCustomField(String key, String value)
    {
        return customFields.put(key, value);
    }

    public String getDisplayName()
    {
        if (displayName != null) {
            return displayName;
        }
        if (firstName != null && lastName != null) {
            return firstName + " " + lastName;
        } else if (firstName != null) {
            return firstName;
        } else if (lastName != null) {
            return lastName;
        } else if (userId != null) {
            return userId;
        } else if (emailAddress != null) {
            return emailAddress;
        }

        return "";
    }

   
   
    public void setNetworkDomain(int networkDomain)
    {
        this.networkDomain = networkDomain;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public long getTrialEndTime()
    {
        return trialEndTime;
    }

    public void setTrialEndTime(long trialEndTime)
    {
        this.trialEndTime = trialEndTime;
    }

    public void setId(String accountId, String corpId) {
        this.id = new Id(accountId, corpId);
    }

  

    public void setGuid(String guid) {
        this.guid = guid;
    }

    
    public String toString()
    {
        return id + "; UserId [" + userId + "]; DisplayName [" + getDisplayName() + "];";
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LoginAccount)) {
            return false;
        }

        LoginAccount confirmed = (LoginAccount) o;

        return id.equals(confirmed.id);
    }

    public int hashCode()
    {
        return id.hashCode();
    }

    private static String replaceNull(String str)
    {
        if (null == str) {
            return "NULL";
        }

        return str;
    }

    private static String replaceString(String str)
    {
        if ("NULL".equals(str)) {
            return null;
        }

        return str;
    }


    public static class Id
            implements Externalizable
    {
        protected String accountId;
        protected String corporateId;

        public Id(String accountId, String corporateId)
        {
            this.accountId = accountId;
            this.corporateId = corporateId;
        }

        public Id()
        {
        }

        public String getAccountId()
        {
            return accountId;
        }

        public String getCorporateId()
        {
            return corporateId;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Id id = (Id) o;

            if (!accountId.equals(id.accountId)) {
                return false;
            }
            if (!corporateId.equals(id.corporateId)) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode()
        {
            int result = accountId.hashCode();
            result = 31 * result + corporateId.hashCode();
            return result;
        }

        

        @Override
        public String toString()
        {
            return "Id{ accountId='" + accountId + '\'' + ", corporateId='" + corporateId + '\'' + '}';
        }

		@Override
		public void readExternal(ObjectInput arg0) throws IOException, ClassNotFoundException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void writeExternal(ObjectOutput arg0) throws IOException {
			// TODO Auto-generated method stub
			
		}
    }


	
}
