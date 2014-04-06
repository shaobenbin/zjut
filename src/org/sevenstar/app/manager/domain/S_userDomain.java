package org.sevenstar.app.manager.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.module.domain.S_moduleDomain;
import org.sevenstar.app.rbac.domain.S_rbac_groupDomain;
import org.sevenstar.app.rbac.domain.S_rbac_permissionDomain;
import org.sevenstar.app.rbac.domain.S_rbac_roleDomain;
import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSCacheModel;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;
import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSManyToOne;
import org.sevenstar.persistent.db.find.annotation.model.SSOneToMany;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;
import org.sevenstar.persistent.db.ibatis.IbatisDao;

@SSDomain(table = "s_user", condition = "sts<>'N'")
@SSId(generateType = "seq", seq = "seq_app")
@SSCacheModel(selectAll = true, flushonexecute = "S_userDomain_base_insert,S_userDomain_base_update,S_userDomain_base_delete,S_org_positionDomain_base_insert,S_org_positionDomain_base_update,S_org_positionDomain_base_delete,S_rbac_groupDomain_base_insert,S_rbac_groupDomain_base_update,S_rbac_groupDomain_base_delete,S_moduleDomain_base_insert,S_moduleDomain_base_update,S_moduleDomain_base_delete,S_rbac_roleDomain_base_insert,S_rbac_roleDomain_base_update,S_rbac_roleDomain_base_delete,S_rbac_permissionDomain_base_insert,S_rbac_permissionDomain_base_update,S_rbac_permissionDomain_base_delete,S_rbac_operationDomain_base_insert,S_rbac_operationDomain_base_update,S_rbac_operationDomain_base_delete,S_rbac_resourceDomain_base_insert,S_rbac_resourceDomain_base_update,S_rbac_resourceDomain_base_delete")
public class S_userDomain extends PersistentObject {

	@SSPrimaryKey
	private Long id;

	private String viewname;

	private String name;

	private String passwd;

	
	
	private String loginid;

	private Date logindate;

	@SSOneToMany(select = "S_rbac_roleDomain_exp_select_role_by_userid")
	private List roleList;

	@SSOneToMany(select = "S_rbac_groupDomain_exp_select_by_userid")
	private List groupList;

	@SSOneToMany(select = "S_org_positionDomain_exp_select_by_userid")
	private List positionList;

	private List allRoleList;

	private List allModuleList;

	private List allPermissionList;
	
	@SSManyToOne(parameter="typeDomain.id",column="type_id")
	private S_user_typeDomain typeDomain;
	
	private String sts;


	public void updateLogindate() {
		IbatisDao.getDao().update("S_userDomain_exp_update_login", this);
	}

	public void update() {
		this.setSts("Y");
		S_userDomain paramDomain = new S_userDomain();
		paramDomain.setName(this.getName());
		if (paramDomain.updateExist(this.getId())) {
			throw new ApplicationException("用户名[" + this.getName() + "]已存在");
		}
		super.update();
	}

	public void insert() {
		this.setSts("Y");
		S_userDomain paramDomain = new S_userDomain();
		paramDomain.setName(this.getName());
		if (paramDomain.exist()) {
			throw new ApplicationException("用户名[" + this.getName() + "]已存在");
		}
		super.insert();
	}

	public void insertOrUpdate() {
		if (this.getId() != null) {
			this.update();
		} else {
			this.insert();
		}
	}

	public void delete() {
		this.setSts("N");
		super.update();
	}

	public boolean hasPermission(S_moduleDomain domain) {
		for (int i = 0; i < getAllModuleList().size(); i++) {
			S_moduleDomain hasModuleDomain = (S_moduleDomain) getAllModuleList()
					.get(i);
			if (hasModuleDomain.getId().equals(domain.getId())) {
				return true;
			}
		}
		return false;
	}

	public List getAllPermissionList() {
		if (allPermissionList != null) {
			return allPermissionList;
		} else {
			allPermissionList = new ArrayList();
		}
		Map map = new HashMap();
		for (int i = 0; i < getAllRoleList().size(); i++) {
			S_rbac_roleDomain roleDomain = (S_rbac_roleDomain) getAllRoleList()
					.get(i);
			List permissionList = roleDomain.getPermissionList();
			for (int j = 0; j < permissionList.size(); j++) {
				S_rbac_permissionDomain permissionDomain = (S_rbac_permissionDomain) permissionList
						.get(j);
				if (!map.containsKey(permissionDomain.getId())) {
					map.put(permissionDomain.getId(), permissionDomain);
					allPermissionList.add(permissionDomain);
				}
			}
		}
		return allPermissionList;
	}

	public List getAllModuleList() {
		if (allModuleList != null) {
			return allModuleList;
		} else {
			allModuleList = new ArrayList();
		}
		Map map = new HashMap();
		for (int i = 0; i < getAllRoleList().size(); i++) {
			S_rbac_roleDomain roleDomain = (S_rbac_roleDomain) getAllRoleList()
					.get(i);
			List moduleList = roleDomain.getModuleList();
			for (int j = 0; j < moduleList.size(); j++) {
				S_moduleDomain moduleDomain = (S_moduleDomain) moduleList
						.get(j);
				if (!map.containsKey(moduleDomain.getId())) {
					map.put(moduleDomain.getId(), moduleDomain);
					allModuleList.add(moduleDomain);
				}
			}
		}
		return allModuleList;
	}

	public List getAllRoleList() {
		if (allRoleList == null) {
			allRoleList = new ArrayList();
			Map map = new HashMap();
			if (this.getGroupList() != null && this.getGroupList().size() > 0) {
				for (int i = 0; i < this.getGroupList().size(); i++) {
					S_rbac_groupDomain groupDomain = (S_rbac_groupDomain) this
							.getGroupList().get(i);
					if (groupDomain.getRoleList().size() > 0) {
						for (int j = 0; j < groupDomain.getRoleList().size(); j++) {
							S_rbac_roleDomain roleDomain = (S_rbac_roleDomain) groupDomain
									.getRoleList().get(j);
							if (!map.containsKey(roleDomain.getId())) {
								map.put(roleDomain.getId(), roleDomain);
								allRoleList.add(roleDomain);
							}
						}
					}
				}
			}
			if (this.getPositionList() != null
					&& this.getPositionList().size() > 0) {
				for (int i = 0; i < this.getPositionList().size(); i++) {
					S_org_positionDomain positionDomain = (S_org_positionDomain) this
							.getPositionList().get(i);
					if (positionDomain.getRoleList().size() > 0) {
						for (int j = 0; j < positionDomain.getRoleList().size(); j++) {
							S_rbac_roleDomain roleDomain = (S_rbac_roleDomain) positionDomain
									.getRoleList().get(j);
							if (!map.containsKey(roleDomain.getId())) {
								map.put(roleDomain.getId(), roleDomain);
								allRoleList.add(roleDomain);
							}
						}
					}
				}
			}
			if (this.getRoleList() != null && this.getRoleList().size() > 0) {
				for (int i = 0; i < this.getRoleList().size(); i++) {
					S_rbac_roleDomain domain = (S_rbac_roleDomain) getRoleList()
							.get(i);
					if (!map.containsKey(domain.getId())) {
						map.put(domain.getId(), domain);
						allRoleList.add(domain);
					}
				}
			}
		}
		return this.allRoleList;
	}

	public S_org_positionDomain getS_org_positionDomain() {
		if (this.getPositionList() != null && this.getPositionList().size() > 0) {
			return (S_org_positionDomain) this.getPositionList().get(0);
		}
		return null;
	}
	
	

	public S_user_typeDomain getTypeDomain() {
		return typeDomain;
	}

	public void setTypeDomain(S_user_typeDomain typeDomain) {
		this.typeDomain = typeDomain;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.Long getId() {
		return this.id;
	}

	public void setViewname(java.lang.String viewname) {
		this.viewname = viewname;
	}

	public java.lang.String getViewname() {
		return this.viewname;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setPasswd(java.lang.String passwd) {
		this.passwd = passwd;
	}

	public java.lang.String getPasswd() {
		return this.passwd;
	}

	public void setSts(java.lang.String sts) {
		this.sts = sts;
	}

	public java.lang.String getSts() {
		return this.sts;
	}

	public List getRoleList() {
		return roleList;
	}

	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}

	public List getGroupList() {
		return groupList;
	}

	public void setGroupList(List groupList) {
		this.groupList = groupList;
	}

	public List getPositionList() {
		return positionList;
	}

	public void setPositionList(List positionList) {
		this.positionList = positionList;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public Date getLogindate() {
		return logindate;
	}

	public void setLogindate(Date logindate) {
		this.logindate = logindate;
	}

}
