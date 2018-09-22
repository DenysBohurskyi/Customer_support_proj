package test.com.entity.data;

import java.util.Objects;

public class QuestionType {

    private String id;
    private Category category;

    public QuestionType(String id, Category category) {
        this.id = id;
        this.category = category;
    }

    public QuestionType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "QuestionType{" +
                "id=" + id +
                ", category=" + category +
                '}';
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static class Category {

        private String id;
        private SubCategory subCategory;

        public Category(String id, SubCategory subCategory) {
            this.id = id;
            this.subCategory = subCategory;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public SubCategory getSubCategory() {
            return subCategory;
        }

        public void setSubCategory(SubCategory subCategory) {
            this.subCategory = subCategory;
        }

        @Override
        public String toString() {
            return "Category{" +
                    "id=" + id +
                    ", subCategory=" + subCategory +
                    '}';
        }

        public static class SubCategory {

            private String id;

            public SubCategory(String id) {
                this.id = id;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            @Override
            public String toString() {
                return "SubCategory{" +
                        "id=" + id +
                        '}';
            }

        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionType)) return false;
        QuestionType that = (QuestionType) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
}
