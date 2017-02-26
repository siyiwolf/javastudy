package com.find.usr.pg;
import java.io.*;
import java.util.*;

public class UsrManager {
	private static int userNum = 0;
	private static String filePath = null;
	private static Map<String, UserAccount> hMap = null;
	private static UsrManager usrManager = null;
	private UsrManager()
	{
		userNum = 0;
		filePath = "D:\\usr.dat";
		hMap = new HashMap<String, UserAccount>();
	};

	public static synchronized UsrManager getInstance()
	{
		if (usrManager == null)
		{
			usrManager = new UsrManager();
			usrManager.deserializeAccount();
		}
		return usrManager;
	}

	public static boolean addUsr(String uName, String uPassword) throws NullPointerException
	{
		if (hMap.containsKey(uName))
		{
			return false;
		}
		
		UserAccount usrAccount = new UserAccount(uName, uPassword);
		hMap.put(uName, usrAccount);
		userNum++;
		return true;
	}
	public static boolean logVerity(String uName, String uPassword)
	{
		if (!(hMap.containsKey(uName)))
		{
			System.out.println("This is not the " + uName);
			return false;
		}
		
		UserAccount usrAccount = (UserAccount)hMap.get(uName);
		return usrAccount.passwordVerity(uPassword);
	}
	
	public static void serializeAccount()
	{
		if (userNum == 0)
		{
			return;
		}
		
		File fWrite = new File(filePath);
		if (!fWrite.exists())
		{
			System.out.println("I will create a file!!");	
			try
			{
				fWrite.createNewFile();
			}
			catch(IOException ie)
			{
				ie.printStackTrace();
			}
		}
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
	
		try
		{
			fos = new FileOutputStream(fWrite);
			oos = new ObjectOutputStream(fos);
			oos.writeInt(userNum);
			Collection<UserAccount> cUser = hMap.values();
			for (UserAccount uAccount : cUser)
			{
				oos.writeObject(uAccount);
			}
		}
		catch(FileNotFoundException fee)
		{
			fee.printStackTrace();
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
		finally
		{
			try
			{
				if(oos != null)
				{
					oos.close();
					oos = null;
				}
				
				if(fos != null)
				{
					fos.close();
					fos = null;
				}
			}
			catch (IOException ie)
			{
				ie.printStackTrace();
			}
		}
	}
	
	public void deserializeAccount()
	{
		File fRead = new File(filePath);
		if (!fRead.exists())
		{
			System.out.println("The file is not exist!!");
			return;
		}
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try
		{
			fis = new FileInputStream(fRead);
			ois = new ObjectInputStream(fis);
			userNum = ois.readInt();
			for (int i = 0; i < userNum; i++)
			{
				UserAccount userAccount = (UserAccount) ois.readObject();
				String uname = userAccount.getuName();
				hMap.put(uname, userAccount);
			}
		}
		catch(FileNotFoundException fee)
		{
			fee.printStackTrace();
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
		catch(ClassNotFoundException cfe)
		{
			if (hMap != null)
			{
				hMap.clear();
			}
			userNum = 0;
			cfe.printStackTrace();
		}
		finally
		{
			try
			{
				if(ois != null)
				{
					ois.close();
					ois = null;
				}
				
				if(fis != null)
				{
					fis.close();
					fis = null;
				}
			}
			catch (IOException ie)
			{
				ie.printStackTrace();
			}
		}  
	}

}