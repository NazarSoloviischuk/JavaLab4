# Лабораторна робота №4 (Java)

Соловійщук Назар Олексійович, група ІО-45

Тема: узагальнене програмування (generics) та wildcards.

## Що в репозиторії

- `simple-task` - Simple Task, завдання 4.2: ієрархія тварин і вольєрів для зоопарку
- `hard-task` - Hard Task: Custom Collections 2.0, власні колекції з лабораторної №2 з підтримкою generics

Обидва проєкти зібрані через Maven.

## Simple Task (4.2)

Ієрархія тварин: `Animal` -> `Mammal` і `Bird`; `Mammal` -> `Lion` і `Ungulate`;
`Ungulate` -> `Zebra` і `Giraffe`; `Bird` -> `Eagle`.

Вольєри побудовані на generics: `Cage<T extends Animal>`, від нього `MammalCage<T extends Mammal>`
і `BirdCage`, а далі `LionCage` та `UngulateCage`. Через це у вольєр для левів можна додати тільки
левів, а у вольєр для копитних тільки зебр і жирафів. Спроба покласти не ту тварину не
компілюється.

Кожен вольєр має максимальну місткість, вміє повертати кількість зайнятих місць, додавати і вилучати
тварин. Якщо місць немає, кидається `CageFullException`, а якщо тварини у вольєрі немає,
`AnimalNotFoundException`.

Клас `Zoo` зберігає список вольєрів `List<Cage<? extends Animal>>` (тут потрібен wildcard, бо
`Cage<Lion>` не є підтипом `Cage<Animal>`) і рахує всіх тварин у зоопарку.

Для вольєрів і зоопарку написані модульні тести (JUnit 5).

## Hard Task

До колекцій з лабораторної №2 додано generics:

- `MyList<E>` - інтерфейс списку
- `MyArrayList<E>` - реалізація на масиві (маркерний інтерфейс `RandomAccess`)
- `MyLinkedList<E>` - реалізація на двозв'язному списку
- `MyLinkedHashSet<E>` - множина, яка пам'ятає порядок додавання (хеш-таблиця і двозв'язний список)
- `MyCache<K, V>` - кеш з LRU-витісненням і часом життя записів, без `null` у ключах та значеннях

Wildcards використані там, де колекція тільки віддає елементи:

- `addAll(Collection<? extends E> c)` у `MyList`, `MyArrayList`, `MyLinkedList`, `MyLinkedHashSet`
- `putAll(Map<? extends K, ? extends V> m)` у `MyCache`

Завдяки цьому в `MyList<Number>` можна додати `List<Integer>`. Це перевіряє `WildcardTest`.
На всі колекції є модульні тести.

## Як запустити

Відкрити потрібну папку (`simple-task` або `hard-task`) в IntelliJ IDEA як проєкт.
`Main` запускається звичайно. Тести запускаються з папки `src/test/java` через Run 'All Tests'
або командою `mvn test`.

## Версії

JDK 21 (Amazon Corretto), JUnit 5.11.0. Новіші версії Java я не використовував, бо інструменти
в лабораторній №2 (EqualsVerifier) їх ще не підтримували.
