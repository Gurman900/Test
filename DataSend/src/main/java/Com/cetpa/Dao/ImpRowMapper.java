package Com.cetpa.Dao;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import Com.cetpa.UploadMainFile;

public class ImpRowMapper implements RowMapper<UploadMainFile>
{

	public UploadMainFile mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		InputStream input=null;
		if(rs!=null)
		{
			Blob b= rs.getBlob("file");
			input =b.getBinaryStream();
		}
		return new UploadMainFile(rs.getString("name"),rs.getString("email"),input,rs.getInt("passcode"));
	}

}