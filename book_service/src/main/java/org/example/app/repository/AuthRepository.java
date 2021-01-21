package org.example.app.repository;

public interface AuthRepository<T> {
    boolean authenticate(T user);
    boolean is_user_exist(T user);
    void store(T user);
}
