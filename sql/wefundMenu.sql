-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('微信支付资金账单', '2049', '1', 'wefund', 'pay/wefund/index', 1, 0, 'C', '0', '0', 'pay:wefund:list', '#', 'admin', sysdate(), '', null, '微信支付资金账单菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('微信支付资金账单查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'pay:wefund:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('微信支付资金账单新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'pay:wefund:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('微信支付资金账单修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'pay:wefund:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('微信支付资金账单删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'pay:wefund:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('微信支付资金账单导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'pay:wefund:export',       '#', 'admin', sysdate(), '', null, '');