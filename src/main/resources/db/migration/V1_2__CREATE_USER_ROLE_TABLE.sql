CREATE TABLE IF NOT EXISTS public.user_role (
    role_id varchar(32),
    name varchar(96) NOT NULL,
    permissions varchar(32),
    CONSTRAINT user_role_pkey PRIMARY KEY (role_id),
    CONSTRAINT user_role_permission_fk
        FOREIGN KEY (permissions)
        REFERENCES public.user_permission(permission_id)
        ON DELETE NO ACTION
        ON UPDATE CASCADE
);