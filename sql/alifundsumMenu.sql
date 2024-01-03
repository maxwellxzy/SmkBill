-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('支付宝资金汇总', '2049', '1', 'alifundsum', 'pay/alifundsum/index', 1, 0, 'C', '0', '0', 'pay:alifundsum:list', '#', 'admin', sysdate(), '', null, '支付宝资金汇总菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('支付宝资金汇总查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'pay:alifundsum:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('支付宝资金汇总新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'pay:alifundsum:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('支付宝资金汇总修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'pay:alifundsum:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('支付宝资金汇总删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'pay:alifundsum:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('支付宝资金汇总导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'pay:alifundsum:export',       '#', 'admin', sysdate(), '', null, '');