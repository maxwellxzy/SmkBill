-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('支付宝交易明细单', '2049', '1', 'alitrade', 'pay/alitrade/index', 1, 0, 'C', '0', '0', 'pay:alitrade:list', '#', 'admin', sysdate(), '', null, '支付宝交易明细单菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('支付宝交易明细单查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'pay:alitrade:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('支付宝交易明细单新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'pay:alitrade:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('支付宝交易明细单修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'pay:alitrade:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('支付宝交易明细单删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'pay:alitrade:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('支付宝交易明细单导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'pay:alitrade:export',       '#', 'admin', sysdate(), '', null, '');