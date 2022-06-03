CREATE TYPE account_status as ENUM('HOLD', 'CLOSED', 'OPEN');
CREATE TABLE IF NOT EXISTS public.account (
   account_id varchar(32) NOT NULL,
   created_at timestamp without time zone,
   expiration_date timestamp without time zone,
   status account_status,
   payments varchar(32),
   account_histories varchar(32),
   customer varchar(32),
   location varchar(32),
   account_type varchar(32),
   CONSTRAINT account_pkey PRIMARY KEY (account_id),
   CONSTRAINT account_payment_fk
       FOREIGN KEY (payments)
           REFERENCES public.payment(payment_id)
           ON DELETE CASCADE
           ON UPDATE NO ACTION,
   CONSTRAINT account_location_fk
       FOREIGN KEY (location)
           REFERENCES public.location(location_id)
           ON DELETE NO ACTION
           ON UPDATE CASCADE,
   CONSTRAINT account_account_type_fk
       FOREIGN KEY (account_type)
           REFERENCES public.account_type(type_id)
           ON DELETE NO ACTION
           ON UPDATE CASCADE,
   CONSTRAINT account_customer_fk
       FOREIGN KEY (customer)
           REFERENCES public.customer(customer_id)
           ON DELETE NO ACTION
           ON UPDATE CASCADE,
   CONSTRAINT account_account_histories_fk
       FOREIGN KEY (account_histories)
           REFERENCES public.account_history(account_history_id)
           ON DELETE CASCADE
           ON UPDATE NO ACTION
);