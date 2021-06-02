package PhoneBookPack;

import java.util.List;

public interface PhoneBookDAO {
	public List<PhoneBookVO> getList();
	public List<PhoneBookVO> search(String keyword);
	public boolean delete(Long id);
	public boolean insert(PhoneBookVO vo);
	
}
