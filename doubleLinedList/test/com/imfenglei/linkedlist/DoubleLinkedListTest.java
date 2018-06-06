package com.imfenglei.linkedlist;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * ˫���������
 * @author ������
 *
 */
public class DoubleLinkedListTest {
	
	DoubleLinkedList<Integer> linkedList = null;
	
	@Before
	public void clear() {
		linkedList = new DoubleLinkedList<>();
	}
	
	/**
	 * ����addFirst����
	 */
	@Test
	public void testAddFirst() {
		linkedList.addFirst(23);
		assertEquals(1, linkedList.size); // ��С
		assertEquals(23, linkedList.get(0)); // ��һ��Ԫ��
	}
	
	/**
	 * ����getFirst����
	 */
	@Test
	public void testGetFirst() {
		linkedList.add(23);
		linkedList.add(12);
		assertEquals(23, linkedList.getFirst());// getFirst����
	}
	
	/**
	 * ����getLast����
	 */
	@Test
	public void testGetLast() {
		linkedList.add(12);
		linkedList.add(23);
		assertEquals(23, linkedList.getLast());// getLast����
	}

	/**
	 * ����addLast����
	 */
	@Test
	public void testAddLast() {
		linkedList.addLast(23);
		assertEquals(23, linkedList.get(0));
		linkedList.addLast(12);
		assertEquals(12, linkedList.get(1));
	}

	/**
	 * ����add����
	 */
	@Test
	public void testAdd() {
		linkedList.add(23);
		assertEquals(23, linkedList.get(0));
		linkedList.add(12);
		assertEquals(12, linkedList.get(linkedList.size - 1));
	}

	/**
	 * ����ɾ����һ������
	 */
	@Test
	public void testRemoveFirst() {
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(4);
		assertEquals(1, linkedList.getFirst());
		linkedList.removeFirst();
		assertEquals(2, linkedList.getFirst());
		linkedList.removeFirst();
		assertEquals(3, linkedList.getFirst());
	}

	/**
	 * ����removeLast����
	 */
	@Test
	public void testRemoveLast() {
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(4);
		assertEquals(4, linkedList.getLast());
		linkedList.removeLast();
		assertEquals(3, linkedList.getLast());
		linkedList.removeLast();
		assertEquals(2, linkedList.getLast());
		
	}
	
	/**
	 * ����ɾ������
	 */
	@Test
	public void testRemove() {
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(4);
		assertEquals(4, linkedList.getLast());
		linkedList.remove(3);
		assertEquals(3, linkedList.getLast());
		linkedList.remove(0);
		assertEquals(2, linkedList.getFirst());
		
	}
	
	/**
	 * ����toArray����
	 */
	@Test
	public void testToArray() {
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(4);
		
		Object[] objects = linkedList.toArray();
		assertEquals(1, objects[0]);
		assertEquals(2, objects[1]);
		assertEquals(3, objects[2]);
		assertEquals(4, objects[3]);
	}
	
	/**
	 * ���Է�ת������
	 */
	@Test
	public void testReverse() {
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(4);
		
		DoubleLinkedList<Integer> reverseLinedList = linkedList.reverse();
		assertEquals(4, reverseLinedList.size);// reverse֮��size����4
		// ���ڵ�ֵ��ת
		assertEquals(4, reverseLinedList.get(0));
		assertEquals(3, reverseLinedList.get(1));
		assertEquals(2, reverseLinedList.get(2));
		assertEquals(1, reverseLinedList.get(3));
	}
	
}
