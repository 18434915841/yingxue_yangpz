package com.baizhi.service;

import com.baizhi.annotation.AddLog;
import com.baizhi.dao.CategoryMapper;
import com.baizhi.entity.Category;
import com.baizhi.entity.CategoryExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CateServiceImpl implements CateService{
        @Resource
        CategoryMapper categoryMapper;

    @Override
    public HashMap<String, Object> queryAll(Integer page,Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        CategoryExample example = new CategoryExample();
        example.createCriteria().andLevelsEqualTo(1);
        //查询总条数
        int count = categoryMapper.selectCountByExample(example);
        map.put("count",count);
        //查询总页数
        int total=count%rows==0?count/rows:count/rows+1;
        map.put("total",total);
        RowBounds rowBounds = new RowBounds(0,rows);
        List<Category> list = categoryMapper.selectByExampleAndRowBounds(example, rowBounds);
        map.put("rows",list);
        return map;
    }
    @AddLog(value = "添加一级类别")
    @Override
    public void insertCate(Category category) {

        category.setId(UUID.randomUUID().toString());
        category.setLevels(1);
        categoryMapper.insert(category);
    }
    @AddLog(value = "删除一级类别")
    @Override
    public HashMap<String,Object> deleteCate(Category category) {

        HashMap<String, Object> map = new HashMap<>();
//        CategoryExample example = new CategoryExample();
//        example.createCriteria().andParentIdEqualTo(category.getId());
//        int count = categoryMapper.selectCountByExample(example);
//        if(count==0){
//            map.put("message","该一级类别下有二级类别,不能删除");
//            map.put("status",100);
//        }else {
//            categoryMapper.deleteByPrimaryKey(category.getId());
//            map.put("message","删除成功");
//            map.put("status","104");
//        }
        //设置查询条件
        CategoryExample example = new CategoryExample();
        example.createCriteria().andParentIdEqualTo(category.getId());

        //根据一级类别id查询对应的子类别的数量
        int count = categoryMapper.selectCountByExample(example);
        if(count==0){
            //没有  直接删除
            categoryMapper.delete(category);
            map.put("message","删除一级类别成功");
            map.put("status",100);
        }else{
            //有 不能删除 给出提示信息
            map.put("message","该类别下有二级类别不能删除!!!");
            map.put("status",104);
        }
        return map;
    }

    @Override
    @AddLog(value = "修改一级类别")
    public void upadateCate(Category category) {
            categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public HashMap<String, Object> queryTwoAll(Integer page, Integer rows, String parentId) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        CategoryExample example = new CategoryExample();
         example.createCriteria().andParentIdEqualTo(parentId);

        //查询总条数
        int count = categoryMapper.selectCountByExample(example);
        System.out.println(count);
        map.put("count",count);
        //查询总页数
        int total=count%rows==0?count/rows:count/rows+1;
        map.put("total",total);
        RowBounds rowBounds = new RowBounds(0,rows);

        List<Category> list = categoryMapper.selectByExampleAndRowBounds(example, rowBounds);
        map.put("rows",list);
        return map;

    }

    @AddLog(value = "添加二级类别")
    @Override
    public void insertCate2(Category category) {
        HashMap<String, Object> map = new HashMap<>();
        category.setId(UUID.randomUUID().toString());
        category.setLevels(2);
        category.setParentId(category.getParentId());
        categoryMapper.insert(category);
//        map.put("message",category.getParentId());
//        return map;
    }

    @AddLog(value = "删除二级类别")
    @Override
    public void deleteCate2(Category category) {
        categoryMapper.deleteByPrimaryKey(category.getId());
    }


    @AddLog(value = "修改二级类别")
    @Override
    public void upadateCate2(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }
}
