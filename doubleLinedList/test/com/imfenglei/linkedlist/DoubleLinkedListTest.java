package com.imfenglei.linkedlist;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * 双向链表测试
 * @author 刘锋雷
 *
 */
public class DoubleLinkedListTest {
	
	DoubleLinkedList<Integer> linkedList = null;
	
	@Before
	public void clear() {
		linkedList = new DoubleLinkedList<>();
	}
	
	/**
	 * 测试addFirst方法
	 */
	@Test
	public void testAddFirst() {
		linkedList.addFirst(23);
		assertEquals(1, linkedList.size); // 大小
		assertEquals(23, linkedList.get(0)); // 第一个元素
	}
	
	/**
	 * 测试getFirst方法
	 */
	@Test
	public void testGetFirst() {
		linkedList.add(23);
		linkedList.add(12);
		assertEquals(23, linkedList.getFirst());// getFirst方法
	}
	
	/**
	 * 测试getLast方法
	 */
	@Test
	public void testGetLast() {
		linkedList.add(12);
		linkedList.add(23);
		assertEquals(23, linkedList.getLast());// getLast方法
	}

	/**
	 * 测试addLast方法
	 */
	@Test
	public void testAddLast() {
		linkedList.addLast(23);
		assertEquals(23, linkedList.get(0));
		linkedList.addLast(12);
		assertEquals(12, linkedList.get(1));
	}

	/**
	 * 测试add方法
	 */
	@Test
	public void testAdd() {
		linkedList.add(23);
		assertEquals(23, linkedList.get(0));
		linkedList.add(12);
		assertEquals(12, linkedList.get(linkedList.size - 1));
	}

	/**
	 * 测试删除第一个方法
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
	 * 测试removeLast方法
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
	 * 测试删除方法
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
	 * 测试toArray方法
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
	 * 测试反转链表方法
	 */
	@Test
	public void testReverse() {
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(4);
		
		DoubleLinkedList<Integer> reverseLinedList = linkedList.reverse();
		assertEquals(4, reverseLinedList.size);// reverse之后，size还是4
		// 各节点值反转
		assertEquals(4, reverseLinedList.get(0));
		assertEquals(3, reverseLinedList.get(1));
		assertEquals(2, reverseLinedList.get(2));
		assertEquals(1, reverseLinedList.get(3));
	}
	
}
