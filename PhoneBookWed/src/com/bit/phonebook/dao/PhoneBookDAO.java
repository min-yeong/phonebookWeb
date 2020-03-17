package com.bit.phonebook.dao;

import java.util.List;
import com.bit.phonebook.vo.PhoneBookVO;

public interface PhoneBookDAO {
	public List<PhoneBookVO> getList();
	public boolean insert(PhoneBookVO phonebookVO);
	public boolean delete(Long id);
	public List<PhoneBookVO> get(String name);
}
