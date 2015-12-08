package com.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import com.bean.PMenu;
import com.bean.PUser;
import com.opensymphony.xwork2.ActionSupport;
import com.serviceimpl.UtilSupport;
import com.util.TreeNode;

@SuppressWarnings("serial")
public class PMenuAction extends ActionSupport {
	private UtilSupport util;
	private List<PMenu> menulis=new ArrayList<PMenu>();
	private List<TreeNode> userMenuTree= new ArrayList<TreeNode>();

	public UtilSupport getUtil() {
		return util;
	}

	public void setUtil(UtilSupport util) {
		this.util = util;
	}

	public List<PMenu> getMenulis() {
		return menulis;
	}

	public void setMenulis(List<PMenu> menulis) {
		this.menulis = menulis;
	}
	
	public List<TreeNode> getChildTreeNodes() {
		return userMenuTree;
	}

	public void setChildTreeNodes(List<TreeNode> tnList) {
		this.userMenuTree = tnList;
	}
	
	public List<TreeNode> getUserMenuTree() {
		return userMenuTree;
	}

	public void setUserMenuTree(List<TreeNode> tnList) {
		this.userMenuTree = tnList;
	}

	/**
	 * 根据用户ID获取用户功能菜单
	 * @return
	 */
	public String getNodes(){
		HttpServletRequest request=ServletActionContext.getRequest();
		PUser loginuser=(PUser)request.getSession().getAttribute("pu");
		String userId=loginuser.getUserId();
		
		getChildMenu(userId);

		return SUCCESS;
	}
	
	// 返回指定用户的功能菜单树（JSON 格式）
	@SuppressWarnings("unchecked")
	public void getChildMenu(String userId)
	{
		// 从数据库获取完整菜单列表
		userMenuTree.clear();
		
		// 从数据库获取完整菜单列表，带用户标识
		menulis = util.getNodesByUserId(userId);
		
	    // 创建根结点
		for(PMenu m : menulis){
			if (m.getPmid().equals("0")){
				TreeNode r = copyPMenuToTreeNode(m);
				// 向根结点添加所有子结点
				addChildren(r);
				// 保存到 TreeNode List 供页面 easyui.tree 调用
				userMenuTree.add(r);
			}
		}
	}


	private int addChildren(TreeNode node)
	{
		int cnt = 0;
	    for(PMenu m : menulis)
	    {
	        if (m.getPmid().equals(node.getId())) // find child menus of given node
	        {        
	            TreeNode c = copyPMenuToTreeNode(m);

	            cnt = (m.getUserId().getUserId()!=null) ? 1 :0;
	            cnt+=cnt;
	            
	            cnt += addChildren(c); // add children for this node
	            if (cnt > 0)
	            {
	            	node.getChildren().add(c);
	            }
	        }
	    }
	    return node.getChildren().size();
	}

	// 拷贝 PMenu 对象到 TreeNode
	private TreeNode copyPMenuToTreeNode(PMenu m) {
		TreeNode c = new TreeNode();
		c.setId(m.getMid());
		c.setText(m.getMname());
		c.setChecked("true");
		c.setAttributes(m.getMurl());
		
		c.setChildren(new ArrayList<TreeNode>());
		return c;
	}
	
}
