package com.carsale.cars.model.records;

import com.carsale.cars.model.User;

public record UserData(
        Long id,
        String username,
        Boolean active
) {
    public UserData(User user) {
        this(user.getId(), user.getUsername(), user.getActive());
    }
}
