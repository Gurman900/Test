package Com.cetpa.Dao;

import org.springframework.jdbc.core.JdbcTemplate;


import Com.cetpa.UploadMainFile;

public class ImpMyDao implements myDao 
{

	JdbcTemplate template;
	public int storeDataInDb(UploadMainFile ump) 
	{
		return template.update("insert into files values(?,?,?,?)", ump.getName(),ump.getEmail(),ump.getInput(),ump.getPassword());
	}

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public UploadMainFile fetchDataFromDb(int password)
	{
		
		return template.queryForObject("select * from files where passcode=?", new ImpRowMapper(),password);
	}

}
