package com.group10.TalesOfWonder.comic;

import com.group10.TalesOfWonder.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class CategoryRepositoryTest {
    @Autowired
    public CategoryRepository categoryRepository;

    @Test
    public void createCategory() {
        Category category = new Category("Manhua","Truyện tranh của Trung Quốc");
        Category category1 = new Category("Manhwa","Truyện tranh Hàn Quốc, đọc từ trái sang phải");
        Category category2 = new Category("Martial Arts","Giống với tên gọi, bất cứ gì liên quan đến võ thuật trong truyện từ các trận đánh nhau, tự vệ đến các môn võ thuật như akido, karate, judo hay taekwondo, kendo, các cách né tránh");
        Category category3 = new Category("Manga","Truyện tranh của Nhật Bản");
        Category category4 = new Category("Horror","Horror là: rùng rợn, nghe cái tên là bạn đã hiểu thể loại này có nội dung thế nào. Nó làm cho bạn kinh hãi, khiếp sợ, ghê tởm, run rẩy, có thể gây sock - một thể loại không dành cho người yếu tim");
        categoryRepository.saveAll(List.of(category,category1,category2,category3,category4));
//        categoryRepository.save(category);
    }
    @Test
    public void testPath() {
        String dirName = "user-photos";

        Path userPhotoPath = Paths.get(dirName);

        String userPhotosPath = userPhotoPath.toFile().getAbsolutePath();
        System.out.println(userPhotosPath);
    }
}