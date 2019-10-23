# Java_NetBean_DemoATM
Mô tả chức năng
    1. NỘP TIỀN.
Khách hàng nhập vào số tiền muốn nộp, sau khi giao dịch thành công, hệ thống sẽ upload dữ liệu lên csdl.
    2. KIỂM TRA SỐ DƯ.
Khách hàng có thể tra cứu số dư còn lại trong thẻ.
    3. CHUYỂN TIỀN.
Khách hàng có thể chuyển tiền từ tài khoản của mình sang tài khoản khác. Với một số ràng buộc số tiền còn lại sau khi chuyển phải lớn hơn 50000, số tiền chuyển phải là bội số của 10000 và không được quá số dư trong tài khoản, không được chuyển tiền cho bản thân.
    4. RÚT TIỀN.
Khách hàng có thể nhập vào số tiền muốn rút. Khi giao dịch có một số ràng buộc như là số tiền muốn rút phải là bội của 50000 và không được nhiều hơn số dư trong tài khoản, số tiền còn lại sau thi thực hiện giao dịch phải lớn hơn 50000
    5. ĐỔI MẬT KHẨU.
Khách hàng có thể thay đổi mật khẩu của tài khoản
    6. TRA CỨU VÀ XUẤT THÔNG TIN.
Khách hàng có thể tra cứu thông tin cá nhân (tên tài khoản, số tài khoản, số dư) và xuất chúng ra file .txt.
    7. TẠO TÀI KHOẢN MỚI .
Admin có quyền mở một tài khoản mới với một số ràng buộc như là: nhập mật khẩu xác nhận phải khớp với mật khẩu mới, số tài khoản không được trùng với bất kì số tài khoản nào trong csdl, số tiền nạp vào phải từ 50000 trở lên.
    8. LỊCH SỬ GIAO DỊCH.
Sao lưu tất cả các giao dịch của các khách hàng làm thay đổi số dư(rút tiền, nạp tiền, chuyển tiền) vào file txt
