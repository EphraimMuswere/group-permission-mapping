create table groups_roles
(
    group_id bigint not null,
    role_id  bigint not null,
    primary key (group_id, role_id)
)