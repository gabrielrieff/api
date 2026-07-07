CREATE TABLE registration(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    registered_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    cancelled_at TIMESTAMP DEFAULT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) NOT NULL DEFAULT 'confirmado',
    event_id UUID NOT NULL,
    user_id UUID NOT NULL,
    FOREIGN KEY (event_id) REFERENCES event(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE INDEX idx_registration_event_id ON registration (event_id);
CREATE INDEX idx_registration_user_id ON registration (user_id);

CREATE UNIQUE INDEX idx_registration_user_event_active
ON registration (user_id, event_id)
WHERE status = 'confirmado';

ALTER TABLE event ADD COLUMN max_participants INTEGER;
ALTER TABLE event ADD COLUMN confirmed_participants INTEGER;
ALTER TABLE event ADD COLUMN version BIGINT NOT NULL DEFAULT 0;