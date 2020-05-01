package Com.cetpa.Dao;

import Com.cetpa.UploadMainFile;

public interface myDao 
{
	public abstract int storeDataInDb(UploadMainFile ump);
	
	public abstract UploadMainFile fetchDataFromDb(int password);
	

}
