-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('业务分类', '2049', '1', 'tradetype', 'pay/tradetype/index', 1, 0, 'C', '0', '0', 'pay:tradetype:list', '#', 'admin', sysdate(), '', null, '业务分类菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('业务分类查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'pay:tradetype:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('业务分类新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'pay:tradetype:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('业务分类修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'pay:tradetype:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('业务分类删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'pay:tradetype:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('业务分类导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'pay:tradetype:export',       '#', 'admin', sysdate(), '', null, '');