CREATE TABLE audits (
    audit_id SERIAL PRIMARY KEY,
    affected_table VARCHAR(255),
    operation_type VARCHAR(10),
    who_user VARCHAR(255),
    date_hour TIMESTAMP,
    old TEXT,
    new TEXT
);
CREATE OR REPLACE FUNCTION register_change()
RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'DELETE' THEN
        INSERT INTO audits(affected_table, operation_type, who_user, date_hour, old, new)
        VALUES (TG_TABLE_NAME, TG_OP, SESSION_USER, NOW(), row_to_json(OLD), row_to_json(NEW));
        RETURN OLD;
    ELSIF TG_OP = 'UPDATE' THEN
        INSERT INTO audits(affected_table, operation_type, who_user, date_hour, old, new)
        VALUES (TG_TABLE_NAME, TG_OP, SESSION_USER, NOW(), row_to_json(NEW), row_to_json(NEW));
        RETURN NEW;
    ELSIF TG_OP = 'INSERT' THEN
        INSERT INTO audits(affected_table, operation_type, who_user, date_hour, old, new)
        VALUES (TG_TABLE_NAME, TG_OP, SESSION_USER, NOW(), row_to_json(NEW), row_to_json(NEW));
        RETURN NEW;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;



CREATE TRIGGER trigger_register_change_accommodation_types
AFTER INSERT OR UPDATE OR DELETE ON accommodation_types
FOR EACH ROW EXECUTE FUNCTION register_change();

CREATE TRIGGER trigger_register_change_accommodation
AFTER INSERT OR UPDATE OR DELETE ON accommodations
FOR EACH ROW EXECUTE FUNCTION register_change();

CREATE TRIGGER trigger_register_change_accommodations_tourist_plans
AFTER INSERT OR UPDATE OR DELETE ON accommodations_tourist_plans
FOR EACH ROW EXECUTE FUNCTION register_change();


CREATE TRIGGER trigger_register_change_admins
AFTER INSERT OR UPDATE OR DELETE ON admins
FOR EACH ROW EXECUTE FUNCTION register_change();

CREATE TRIGGER trigger_register_change_attractive_places
AFTER INSERT OR UPDATE OR DELETE ON atractive_places
FOR EACH ROW EXECUTE FUNCTION register_change();

CREATE TRIGGER trigger_register_change_reservations
AFTER INSERT OR UPDATE OR DELETE ON reservations
FOR EACH ROW EXECUTE FUNCTION register_change();

CREATE TRIGGER trigger_register_change_tourist_destinations_tourist_plans
AFTER INSERT OR UPDATE OR DELETE ON tourist_destination_tourist_plans
FOR EACH ROW EXECUTE FUNCTION register_change();

CREATE TRIGGER trigger_register_change_tourist_destinations
AFTER INSERT OR UPDATE OR DELETE ON tourist_destinations
FOR EACH ROW EXECUTE FUNCTION register_change();

CREATE TRIGGER trigger_register_change_tourist_plans
AFTER INSERT OR UPDATE OR DELETE ON tourist_plans
FOR EACH ROW EXECUTE FUNCTION register_change();

CREATE TRIGGER trigger_register_change_users
AFTER INSERT OR UPDATE OR DELETE ON users
FOR EACH ROW EXECUTE FUNCTION register_change();

CREATE TRIGGER trigger_register_change_transport_methods
AFTER INSERT OR UPDATE OR DELETE ON transport_methods
FOR EACH ROW EXECUTE FUNCTION register_change();
