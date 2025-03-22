package edu.poly.pd11347_asm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class SessionService {
    @Autowired
    private HttpSession session;

    // Đọc giá trị của attribute trong session với kiểm tra kiểu dữ liệu
    public <T> T get(String name, Class<T> type) {
        Object value = session.getAttribute(name);
        if (type.isInstance(value)) {
            return type.cast(value);
        }
        return null;
    }

    // Thay đổi hoặc tạo mới attribute trong session
    public void set(String name, Object value) {
        session.setAttribute(name, value);
    }

    // Xóa attribute trong session
    public void remove(String name) {
        session.removeAttribute(name);
    }

    // Kiểm tra xem session có chứa attribute không
    public boolean contains(String name) {
        return session.getAttribute(name) != null;
    }

    // Xóa toàn bộ session (logout)
    public void clear() {
        session.invalidate();
    }
}
