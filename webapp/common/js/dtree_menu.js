a = new dTree('a');
a.config.folderLinks=true;
a.config.useStatusText=true;
a.config.useCookies=false;
a.config.closeSameLevel=false;
a.add(0,-1,' 系统管理','pages/systemanage/qyxxygl.htm','','mainFrame');

a.add(200, 0,'权限管理','','','mainFrame');
a.add(21, 200,'用户管理','pages/systemanage/qyxxygl.htm','','mainFrame');
a.add(22, 200,'角色管理','','','mainFrame');
a.add(23, 22,'公司管理','welcome.htm','','mainFrame');
a.add(24, 22,'部门管理','welcome.htm','','mainFrame');
a.add(25, 200,'功能模块管理','welcome.htm','','mainFrame');


a.add(201, 0,'参数设置','','','mainFrame');
a.add(32, 201,'资产目录设置','welcome.htm','','mainFrame');
a.add(33, 201,'资产地点设置','welcome.htm','','mainFrame');
a.add(36, 201,'专业类别维护','welcome.htm','','mainFrame');

a.add(39, 35,'产品包审核','welcome.htm','','mainFrame');
a.add(40, 35,'产品包发布','welcome.htm','','mainFrame');

a.add(202, 0,'数据同步','welcome.htm','','mainFrame');

a.add(203, 0,'灵活字段设置','welcome.htm','','mainFrame');
a.add(42, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(43, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(44, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(45, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(46, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(47, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(49, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(49, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(50, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(51, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(52, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(53, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(54, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(55, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(56, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(57, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(58, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(59, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(62, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(63, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(64, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(65, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(66, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(67, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(69, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(69, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(70, 203,'灵活字段活字段设活字段设设置','welcome.htm','','mainFrame');
a.add(71, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(72, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(73, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(74, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(75, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(76, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(77, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(78, 203,'灵活字段设置','welcome.htm','','mainFrame');
a.add(79, 203,'灵活字段设置','welcome.htm','','mainFrame');

document.write(a);
eval("a.closeAll();");