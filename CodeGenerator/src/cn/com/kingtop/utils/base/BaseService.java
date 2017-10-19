package cn.com.kingtop.utils.base;

import java.util.List;

/**
 * 
 * @author jiangjiaxin
 * @date 2017-10-19 下午3:28:56
 */
public interface BaseService<K, T extends BaseModel> {

	T selectByPrimaryKey(K id);

	T selectByModel(T model);

	List<T> list(T model);

	List<T> page(T model);

	int count(T model);

	long max(K id);

	void insert(T model);

	void delete(K id);

	void update(T model);

	void updateCondition(T model, T where);
}
