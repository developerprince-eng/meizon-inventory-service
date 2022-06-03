CREATE TABLE IF NOT EXISTS public.product (
    product_sku varchar(16) NOT NULL,
    name varchar(96),
    description text,
    created_at timestamp without time zone,
    rates varchar(32),
    categories varchar(32),
    type varchar(32),
    CONSTRAINT product_pkey PRIMARY KEY (product_sku),
    CONSTRAINT product_rate_fk
    FOREIGN KEY (rates)
       REFERENCES public.rate(rate_id)
       ON DELETE NO ACTION
       ON UPDATE CASCADE,
    CONSTRAINT product_category_fk
        FOREIGN KEY (categories)
            REFERENCES public.category(category_id)
            ON DELETE NO ACTION
            ON UPDATE CASCADE,
    CONSTRAINT product_product_type_fk
        FOREIGN KEY (type)
            REFERENCES public.product_type(type_id)
            ON DELETE NO ACTION
            ON UPDATE CASCADE
);