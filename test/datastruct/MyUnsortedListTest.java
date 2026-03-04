package datastruct;

import static org.junit.Assert.*;
import org.junit.Test;

public class MyUnsortedListTest {


    @Test
    public void testIsEmptyOnNewList() {
        // Une liste créée avec of() sans arguments doit être vide.
        MyUnsortedList<Integer> list = MyUnsortedList.of();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testIsEmptyAfterAppend() {
        // Après un ajout, la liste ne doit plus être vide.
        MyUnsortedList<Integer> list = MyUnsortedList.of();
        list.append(1);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testIsEmptyAfterPopAll() {
        // Vider entièrement une liste doit rendre isEmpty() == true.
        MyUnsortedList<Integer> list = MyUnsortedList.of(1);
        list.pop();
        assertTrue(list.isEmpty());
    }


    @Test
    public void testSizeEmptyList() {
        assertEquals(0, MyUnsortedList.of().size());
    }

    @Test
    public void testSizeAfterPrepend() {
        MyUnsortedList<Integer> list = MyUnsortedList.of();
        list.prepend(10);
        assertEquals(1, list.size());
        list.prepend(20);
        assertEquals(2, list.size());
    }

    @Test
    public void testSizeAfterAppend() {
        MyUnsortedList<Integer> list = MyUnsortedList.of();
        list.append(1);
        list.append(2);
        list.append(3);
        assertEquals(3, list.size());
    }

    @Test
    public void testSizeAfterInsert() {
        MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
        list.insert(99, 1);
        assertEquals(4, list.size());
    }

    @Test
    public void testSizeAfterPop() {
        // Un bug était ici : size n'était pas décrémenté dans remove()
        MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
        list.pop();
        assertEquals(2, list.size());
    }

    @Test
    public void testSizeAfterPopLast() {
        // Pareil ici, et tout les tests où size était censé être décrementé
        MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
        list.popLast();
        assertEquals(2, list.size());
    }

    @Test
    public void testSizeAfterRemove() {
        MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
        list.remove(1);
        assertEquals(2, list.size());
    }

    @Test
    public void testPrependOnEmptyList() {
        MyUnsortedList<Integer> list = MyUnsortedList.of();
        list.prepend(42);
        assertEquals(MyUnsortedList.of(42), list);
    }

    @Test
    public void testPrependAddsAtFront() {
        MyUnsortedList<Integer> list = MyUnsortedList.of(2, 3);
        list.prepend(1);
        assertEquals(MyUnsortedList.of(1, 2, 3), list);
    }

    @Test
    public void testPrependMultiple() {
        MyUnsortedList<Integer> list = MyUnsortedList.of();
        list.prepend(3);
        list.prepend(2);
        list.prepend(1);
        assertEquals(MyUnsortedList.of(1, 2, 3), list);
    }


    @Test
    public void testAppendOnEmptyList() {
        MyUnsortedList<Integer> list = MyUnsortedList.of();
        list.append(42);
        assertEquals(MyUnsortedList.of(42), list);
    }

    @Test
    public void testAppendAddsAtEnd() {
        MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2);
        list.append(3);
        assertEquals(MyUnsortedList.of(1, 2, 3), list);
    }

    @Test
    public void testAppendMultiple() {
        MyUnsortedList<Integer> list = MyUnsortedList.of();
        list.append(1);
        list.append(2);
        list.append(3);
        assertEquals(MyUnsortedList.of(1, 2, 3), list);
    }

    @Test
    public void testInsertAtPosition0() {
        // pos == 0 doit être équivalent à prepend()
        MyUnsortedList<Integer> list = MyUnsortedList.of(2, 3);
        list.insert(1, 0);
        assertEquals(MyUnsortedList.of(1, 2, 3), list);
    }

    @Test
    public void testInsertAtMiddle() {
        MyUnsortedList<Integer> list = MyUnsortedList.of(1, 3);
        list.insert(2, 1);
        assertEquals(MyUnsortedList.of(1, 2, 3), list);
    }

    @Test
    public void testInsertAtEnd() {
        // pos == size doit être équivalent à append()
        MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2);
        list.insert(3, 2);
        assertEquals(MyUnsortedList.of(1, 2, 3), list);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertNegativePosition() {
        MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
        list.insert(99, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertPositionTooLarge() {
        MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
        list.insert(99, 10);
    }

    @Test(expected = EmptyListException.class)
    public void testPopOnEmptyList() {
        // pop() sur liste vide doit lever EmptyListException
        MyUnsortedList<Integer> list = MyUnsortedList.of();
        list.pop();
    }

    @Test
    public void testPopReturnFirstElement() {
        MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
        assertEquals(Integer.valueOf(1), list.pop());
    }

    @Test
    public void testPopRemovesFirstElement() {
        MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
        list.pop();
        assertEquals(MyUnsortedList.of(2, 3), list);
    }

    @Test
    public void testPopSingleElement() {
        MyUnsortedList<Integer> list = MyUnsortedList.of(42);
        assertEquals(Integer.valueOf(42), list.pop());
        assertTrue(list.isEmpty());
    }

    @Test(expected = EmptyListException.class)
    public void testPopLastOnEmptyList() {
        // Un autre bug était ici : levait IndexOutOfBoundsException au lieu de EmptyListException
        MyUnsortedList<Integer> list = MyUnsortedList.of();
        list.popLast();
    }

    @Test
    public void testPopLastReturnLastElement() {
        MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
        assertEquals(Integer.valueOf(3), list.popLast());
    }

    @Test
    public void testPopLastRemovesLastElement() {
        MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
        list.popLast();
        assertEquals(MyUnsortedList.of(1, 2), list);
    }

    @Test
    public void testPopLastSingleElement() {
        MyUnsortedList<Integer> list = MyUnsortedList.of(42);
        assertEquals(Integer.valueOf(42), list.popLast());
        assertTrue(list.isEmpty());
    }


    @Test
    public void testRemoveAtPosition0() {
        // remove(0) doit être équivalent à pop()
        MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
        assertEquals(Integer.valueOf(1), list.remove(0));
        assertEquals(MyUnsortedList.of(2, 3), list);
    }

    @Test
    public void testRemoveAtMiddle() {
        MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
        assertEquals(Integer.valueOf(2), list.remove(1));
        assertEquals(MyUnsortedList.of(1, 3), list);
    }

    @Test
    public void testRemoveAtLastPosition() {
        MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
        assertEquals(Integer.valueOf(3), list.remove(2));
        assertEquals(MyUnsortedList.of(1, 2), list);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveNegativePosition() {
        MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
        list.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemovePositionTooLarge() {
        MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
        list.remove(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOnEmptyList() {
        MyUnsortedList<Integer> list = MyUnsortedList.of();
        list.remove(0);
    }

    @Test
    public void testEqualsTwoEmptyLists() {
        assertEquals(MyUnsortedList.of(), MyUnsortedList.of());
    }

    @Test
    public void testEqualsIdenticalContent() {
        assertEquals(MyUnsortedList.of(1, 2, 3), MyUnsortedList.of(1, 2, 3));
    }

    @Test
    public void testEqualsDifferentContent() {
        assertNotEquals(MyUnsortedList.of(1, 2, 3), MyUnsortedList.of(1, 2, 4));
    }

    @Test
    public void testEqualsDifferentSize() {
        assertNotEquals(MyUnsortedList.of(1, 2), MyUnsortedList.of(1, 2, 3));
    }
}
