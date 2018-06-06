package com.imfenglei.linkedlist;

/**
 * 双向链表实现
 * 
 * @author 刘锋雷
 * @param <E>
 */
public class DoubleLinkedList<E> {
	// size
	volatile int size = 0;

	// 上一个
	Node<E> p;

	// 下一个
	Node<E> n;

	// 第一个
	Node<E> f;

	// 最后一个
	Node<E> l;

	private static final String ERRMSG_NODE_NOT_FOUND = "删除失败，没有这个元素。";

	private static final String ERRMSG_PARAM_INVALID = "参数错误。";

	/**
	 * 为节点建立关联
	 * 
	 * @param pNode
	 *            当前节点的上一个节点
	 * @param e
	 *            当前节点
	 * @param nNode
	 *            当前节点的下一个节点 当e==null时，此方法作用为删除当前节点，将pNode与nNode建立关联
	 */
	private void link(Node<E> pNode, Node<E> e, Node<E> nNode) {
		// 当e为null时，为解除关联时调用，将当前节点e从链表中删除
		if (e == null) {
			// 上下节点都不为空，将pNode<==>nNode
			if (pNode != null && nNode != null) {
				pNode.next = nNode;
				nNode.prev = pNode;
			} else if (pNode != null && nNode == null) {
				// 上节点不为空，下节点为空，将pNode置为最后一个节点
				pNode.next = null;
				l = pNode;
			} else if (nNode != null && pNode == null) {
				// 上节点为空，下节点不为空，将nNode置为第一个节点
				nNode.prev = null;
				f = nNode;
			} else {
				// 第一个节点和最后一个节点都置为null
				f = null;
				l = null;
			}
			
			//删除第一个节点，要把f重置
			if(pNode != null && pNode.prev == null) {
				f = pNode;
			}
			// 删除最后一个节点，要把l重置
			if(nNode != null && nNode.next == null) {
				l = nNode;
			}
		} else {
			// 当前节点e不为空，则为新增节点
			// 上一个节点为空，则e为第一个节点
			if (pNode == null) {
				f = e;
			} else {
				// 上一个节点不为空，则e为pNode的下一节点
				pNode.next = e;
				e.prev = pNode;
			}

			// 下一个节点为空，则e为最后一个节点
			if (nNode == null) {
				l = e;
			} else {
				// 下一个节点不为空，则e为nNode的上一个节点
				e.next = nNode;
				nNode.prev = e;
			}
		}
	}

	/**
	 * 关联第一个元素
	 * 
	 * @param e
	 */
	private void linkFirstNode(E e) {
		final Node<E> first = f;
		final Node<E> newNode = new Node<>(null, e, null);

		this.link(first, newNode, null);
		if (size == 0) {
			l = f;
		}
		size++;
	}

	/**
	 * 新元素添加到最后
	 * 
	 * @param e
	 */
	private void linkLastNode(E e) {
		final Node<E> last = l;
		final Node<E> newNode = new Node<>(l, e, null);

		this.link(last, newNode, null);
		if (size == 0) {
			f = l;
		}
		size++;
	}

	/**
	 * 新元素添加到第一个
	 * 
	 * @param e
	 */
	public void addFirst(E e) {
		linkFirstNode(e);
	}

	/**
	 * 新元素添加到末尾
	 * 
	 * @param e
	 */
	public void addLast(E e) {
		linkLastNode(e);
	}

	/**
	 * 添加新元素，添加至末尾
	 * 
	 * @param e
	 */
	public void add(E e) {
		addLast(e);
	}

	/**
	 * 解除关联
	 * 
	 * @param pNode
	 *            上一节点
	 * @param nNode
	 *            下一节点
	 */
	private void unLink(Node<E> pNode, Node<E> nNode) {
		this.link(pNode, null, nNode);
		size--;
	}

	/**
	 * 删除第一个
	 */
	public void removeFirst() {
		Node<E> firstNode = f;
		if (null != firstNode) {
			// 第一个节点的下一节点
			Node<E> nextNode = firstNode.next;

			// nextNode == null ，则该链表只有一个元素
			if (nextNode == null) {
				unLink(null, null);
			} else {
				nextNode.prev = null;
				// 将当前第一节点的下一节点与下下节点建立关联，达到删除第一个节点的目的
				unLink(nextNode, nextNode.next);
			}
		} else {
			throw new RuntimeException(ERRMSG_NODE_NOT_FOUND);
		}
	}

	/**
	 * 删除最后一个
	 */
	public boolean removeLast() {
		Node<E> lastNode = l;
		if (null != lastNode) {
			// 最后一个节点的上一节点
			Node<E> prevNode = lastNode.prev;

			// prevNode == null,则该链表只有一个元素
			if (prevNode == null) {
				unLink(null, null);
			} else {
				prevNode.next = null;
				// 将当前最后一节点的上一节点与上上节点建立关联，达到删除最后一节点的目的
				unLink(prevNode.prev, prevNode);
			}
			return true;

		} else {
			throw new RuntimeException(ERRMSG_NODE_NOT_FOUND);
		}
	}

	/**
	 * 删除节点
	 * 
	 * @param e
	 */
	public boolean remove(Object o) {
		// 遍历链表，找到与o相等的元素并删除之
		for (Node<E> node = f; node != null; node = node.next) {
			// 找到对应元素，删除之
			// o == null 时node.item == null, o != null 时,o.equals(node.item)
			if ((o != null && o.equals(node.item)) || (o == null && node.item == null)) {
				unLink(node.prev, node.next);
				return true;
			}
		}
		return false;
	}

	/**
	 * 删除指定位置的节点
	 * 
	 * @param i
	 */
	public boolean remove(int index) {
		Node<E> node = getNodeByIndex(index);
		if (null != node) {
			return remove(node.item);
		} else {
			throw new RuntimeException(ERRMSG_NODE_NOT_FOUND);
		}
	}

	/**
	 * 返回指定位置的节点
	 * 
	 * @param index
	 * @return
	 */
	public Node<E> getNodeByIndex(int index) {
		if (checkIndex(index)) {
			Node<E> n = f;
			for (int i = 0; i < index; i++) {
				n = n.next;
			}
			return n;
		} else {
			throw new RuntimeException(ERRMSG_PARAM_INVALID);
		}
	}

	/**
	 * 得到第一个节点的值 
	 * 
	 * @return
	 */
	public Object getFirst() {
		if(null == f) {
			return null;
		}
		return f.item;
	}

	/**
	 * 得到最后一个节点的值 
	 * 
	 * @return
	 */
	public Object getLast() {
		if(null == l) {
			return null;
		}
		return l.item;
	}

	/**
	 * 根据index获取节点值
	 * 
	 * @param index
	 * @return
	 */
	public Object get(int index) {
		Node<E> node = getNodeByIndex(index);
		return node.item;
	}

	/**
	 * 检查index是否合法
	 * 
	 * @param index
	 * @return
	 */
	private boolean checkIndex(int index) {
		return index >= 0 && index < size;
	}

	/**
	 * 反向输出链表
	 * 
	 * @return
	 */
	public DoubleLinkedList<E> reverse() {
		DoubleLinkedList<E> linkedList = new DoubleLinkedList<>();

		// 遍历链表，依次反转每个节点的pref和next
		for (Node<E> node = l; node != null; node = node.prev) {
			Node<E> newNode = node;
			linkedList.add(newNode.item);
		}
		return linkedList;
	}
	
	/**
	 * 返回数组
	 * @return
	 */
	public Object[] toArray() {
		Object[] objects = new Object[size];
		int i = 0;
		for (Node<E> node = f; node != null; node = node.next) {
			objects[i++] = node.item;
		}
		return objects;
	}

	/**
	 * 节点
	 * @param <E>
	 */
	private static class Node<E> {
		E item;
		Node<E> next;
		Node<E> prev;

		Node(Node<E> prev, E element, Node<E> next) {
			this.item = element;
			this.next = next;
			this.prev = prev;
		}
	}

	public static void main(String[] args) {

	}
}
