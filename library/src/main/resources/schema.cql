CREATE COLUMNFAMILY custom_field (
usage_key text,
id bigint,
created_date timestamp,
updated_date timestamp,
type text,
enabled boolean,
labels map<text,text>,
placeholders map<text,text>,
descriptions map<text,text>,
values list<text>,
values_en list<text>,
values_fr list<text>,
default_value int,
PRIMARY KEY ((usage_key), id)
);

CREATE COLUMNFAMILY custom_field_data (
usage_key text,
parent_type text,
parent_id bigint,
custom_field_id bigint,
created_date timestamp,
updated_date timestamp,
values list<text>,
value text,
PRIMARY KEY ((usage_key), parent_type, parent_id, custom_field_id)
);