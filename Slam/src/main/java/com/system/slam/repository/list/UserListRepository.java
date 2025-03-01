package com.system.slam.repository.list;

import com.system.slam.entity.list.UserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserListRepository extends JpaRepository<UserList, Long> {

    List<UserList> findAllByIdListAndTypeList(Long idList, int typeList);
}
