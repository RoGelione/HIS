package com.dg.dao;

import com.dg.entity.Page;


public interface PageDao<T> {

	/**
	 * <b>function:</b> �����ѯ���Ͳ�ѯ������key��Ӧvalue��pageָ��currentPage��pageSize
	 * @param queryHql ��ѯ���
	 * @param paramMap ����
	 * @param page ��ǰҳ��ÿҳ��������
	 * @throws Exception
	 */
	public Page<T> ShowPage(String queryHql,String countHql, int currentPage, int pageSize) throws Exception;

}