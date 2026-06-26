package com.domain.event;

import java.util.*;

public interface EventAddressProjection {
    UUID getId();
    String getTitle();
    String getDescription();
    Date getDate();
    String getImgUrl();
    String getEventUrl();
    Boolean getRemote();
    String getCity();
    String getUf();
}
