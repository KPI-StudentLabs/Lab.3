import java.util.Arrays;
import java.util.Comparator;

/**
 * Клас будівельного блоку (як в minecraft)
 */
class BuildingBlock {
    // поля класу
    private String name;        // назва блоку
    private String material;    // матеріал з якого зроблений
    private int hardness;       // твердість блоку (0-10)
    private boolean transparent; // чи прозорий блок
    private int id;            // унікальний ідентифікатор

    // конструктор з усіма параметрами
    public BuildingBlock(String name, String material, int hardness, boolean transparent, int id) {
        this.name = name;
        this.material = material;
        this.hardness = hardness;
        this.transparent = transparent;
        this.id = id;
    }

    // гетери для отримання значень полів
    public String getName() {
        return name;
    }

    public String getMaterial() {
        return material;
    }

    public int getHardness() {
        return hardness;
    }

    public boolean isTransparent() {
        return transparent;
    }

    public int getId() {
        return id;
    }

    // сетери для зміни значень полів
    public void setName(String name) {
        this.name = name;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setHardness(int hardness) {
        this.hardness = hardness;
    }

    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Метод для перевірки чи два блоки ідентичні
     * порівнює всі поля
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        BuildingBlock block = (BuildingBlock) obj;
        return hardness == block.hardness &&
                transparent == block.transparent &&
                id == block.id &&
                name.equals(block.name) &&
                material.equals(block.material);
    }

    /**
     * Метод для красивого виводу блоку на екран
     */
    @Override
    public String toString() {
        return String.format("Block{name='%s', material='%s', hardness=%d, transparent=%s, id=%d}",
                name, material, hardness, transparent, id);
    }
}

/**
 * Головний клас з виконавчим методом
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Лабораторна робота №3 ===");

        try {
            // завдання 1 - рахуємо коефіцієнт
            int studentNumber = 10;
            int C11 = studentNumber % 11; // це буде 10

            System.out.println("Порядковий номер: " + studentNumber);
            System.out.println("C11 = " + C11 + " (клас будівельного блоку)");
            System.out.println();

            // завдання 2 - створюю масив об'єктів BuildingBlock
            System.out.println("Створюю масив будівельних блоків:");

            // всі змінні описані тут у виконавчому методі
            BuildingBlock[] blocks = new BuildingBlock[6];

            // заповнюю масив різними блоками
            blocks[0] = new BuildingBlock("Stone", "Rock", 5, false, 1);
            blocks[1] = new BuildingBlock("Glass", "Silicon", 2, true, 2);
            blocks[2] = new BuildingBlock("Wood", "Oak", 3, false, 3);
            blocks[3] = new BuildingBlock("Diamond", "Carbon", 9, false, 4);
            blocks[4] = new BuildingBlock("Ice", "Water", 2, true, 5);
            blocks[5] = new BuildingBlock("Dirt", "Soil", 1, false, 6);

            System.out.println("Початковий масив:");
            printArray(blocks);

            // завдання 3 - сортую масив
            // за id (перше поле) по зростанню, за hardness (друге поле) по спаданню  
            System.out.println("Сортую за id (зростання) та hardness (спадання):");

            Arrays.sort(blocks, new Comparator<BuildingBlock>() {
                @Override
                public int compare(BuildingBlock b1, BuildingBlock b2) {
                    // спочатку порівнюю за id (зростання)
                    int result = Integer.compare(b1.getId(), b2.getId());

                    // якщо id однакові, то порівнюю за hardness (спадання)
                    if (result == 0) {
                        result = Integer.compare(b2.getHardness(), b1.getHardness());
                    }

                    return result;
                }
            });

            System.out.println("Відсортований масив:");
            printArray(blocks);

            // завдання 4 - шукаю ідентичний об'єкт
            BuildingBlock searchBlock = new BuildingBlock("Glass", "Silicon", 2, true, 2);
            System.out.println("Шукаю блок: " + searchBlock);

            int foundIndex = findIdenticalBlock(blocks, searchBlock);

            if (foundIndex != -1) {
                System.out.println("Знайдено ідентичний блок на позиції: " + foundIndex);
                System.out.println("Блок: " + blocks[foundIndex]);
            } else {
                System.out.println("Ідентичний блок не знайдено");
            }

        } catch (Exception e) {
            System.out.println("ПОМИЛКА: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Метод для красивого виводу масиву блоків
     * @param blocks масив для виводу
     */
    public static void printArray(BuildingBlock[] blocks) {
        for (int i = 0; i < blocks.length; i++) {
            System.out.println((i + 1) + ". " + blocks[i]);
        }
        System.out.println();
    }

    /**
     * Метод для пошуку ідентичного блоку в масиві
     * використовую метод equals для порівняння
     * @param blocks масив де шукаємо
     * @param target блок який шукаємо
     * @return індекс знайденого блоку або -1 якщо не знайдено
     */
    public static int findIdenticalBlock(BuildingBlock[] blocks, BuildingBlock target) {
        if (blocks == null || target == null) {
            return -1;
        }

        // проходжу по всьому масиву
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i] != null && blocks[i].equals(target)) {
                return i; // повертаю індекс знайденого блоку
            }
        }

        return -1; // не знайдено
    }
}