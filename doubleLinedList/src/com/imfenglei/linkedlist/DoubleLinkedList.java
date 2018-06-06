package com.imfenglei.linkedlist;

/**
 * ˫������ʵ��
 * 
 * @author ������
 * @param <E>
 */
public class DoubleLinkedList<E> {
	// size
	volatile int size = 0;

	// ��һ��
	Node<E> p;

	// ��һ��
	Node<E> n;

	// ��һ��
	Node<E> f;

	// ���һ��
	Node<E> l;

	private static final String ERRMSG_NODE_NOT_FOUND = "ɾ��ʧ�ܣ�û�����Ԫ�ء�";

	private static final String ERRMSG_PARAM_INVALID = "��������";

	/**
	 * Ϊ�ڵ㽨������
	 * 
	 * @param pNode
	 *            ��ǰ�ڵ����һ���ڵ�
	 * @param e
	 *            ��ǰ�ڵ�
	 * @param nNode
	 *            ��ǰ�ڵ����һ���ڵ� ��e==nullʱ���˷�������Ϊɾ����ǰ�ڵ㣬��pNode��nNode��������
	 */
	private void link(Node<E> pNode, Node<E> e, Node<E> nNode) {
		// ��eΪnullʱ��Ϊ�������ʱ���ã�����ǰ�ڵ�e��������ɾ��
		if (e == null) {
			// ���½ڵ㶼��Ϊ�գ���pNode<==>nNode
			if (pNode != null && nNode != null) {
				pNode.next = nNode;
				nNode.prev = pNode;
			} else if (pNode != null && nNode == null) {
				// �Ͻڵ㲻Ϊ�գ��½ڵ�Ϊ�գ���pNode��Ϊ���һ���ڵ�
				pNode.next = null;
				l = pNode;
			} else if (nNode != null && pNode == null) {
				// �Ͻڵ�Ϊ�գ��½ڵ㲻Ϊ�գ���nNode��Ϊ��һ���ڵ�
				nNode.prev = null;
				f = nNode;
			} else {
				// ��һ���ڵ�����һ���ڵ㶼��Ϊnull
				f = null;
				l = null;
			}
			
			//ɾ����һ���ڵ㣬Ҫ��f����
			if(pNode != null && pNode.prev == null) {
				f = pNode;
			}
			// ɾ�����һ���ڵ㣬Ҫ��l����
			if(nNode != null && nNode.next == null) {
				l = nNode;
			}
		} else {
			// ��ǰ�ڵ�e��Ϊ�գ���Ϊ�����ڵ�
			// ��һ���ڵ�Ϊ�գ���eΪ��һ���ڵ�
			if (pNode == null) {
				f = e;
			} else {
				// ��һ���ڵ㲻Ϊ�գ���eΪpNode����һ�ڵ�
				pNode.next = e;
				e.prev = pNode;
			}

			// ��һ���ڵ�Ϊ�գ���eΪ���һ���ڵ�
			if (nNode == null) {
				l = e;
			} else {
				// ��һ���ڵ㲻Ϊ�գ���eΪnNode����һ���ڵ�
				e.next = nNode;
				nNode.prev = e;
			}
		}
	}

	/**
	 * ������һ��Ԫ��
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
	 * ��Ԫ����ӵ����
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
	 * ��Ԫ����ӵ���һ��
	 * 
	 * @param e
	 */
	public void addFirst(E e) {
		linkFirstNode(e);
	}

	/**
	 * ��Ԫ����ӵ�ĩβ
	 * 
	 * @param e
	 */
	public void addLast(E e) {
		linkLastNode(e);
	}

	/**
	 * �����Ԫ�أ������ĩβ
	 * 
	 * @param e
	 */
	public void add(E e) {
		addLast(e);
	}

	/**
	 * �������
	 * 
	 * @param pNode
	 *            ��һ�ڵ�
	 * @param nNode
	 *            ��һ�ڵ�
	 */
	private void unLink(Node<E> pNode, Node<E> nNode) {
		this.link(pNode, null, nNode);
		size--;
	}

	/**
	 * ɾ����һ��
	 */
	public void removeFirst() {
		Node<E> firstNode = f;
		if (null != firstNode) {
			// ��һ���ڵ����һ�ڵ�
			Node<E> nextNode = firstNode.next;

			// nextNode == null ���������ֻ��һ��Ԫ��
			if (nextNode == null) {
				unLink(null, null);
			} else {
				nextNode.prev = null;
				// ����ǰ��һ�ڵ����һ�ڵ������½ڵ㽨���������ﵽɾ����һ���ڵ��Ŀ��
				unLink(nextNode, nextNode.next);
			}
		} else {
			throw new RuntimeException(ERRMSG_NODE_NOT_FOUND);
		}
	}

	/**
	 * ɾ�����һ��
	 */
	public boolean removeLast() {
		Node<E> lastNode = l;
		if (null != lastNode) {
			// ���һ���ڵ����һ�ڵ�
			Node<E> prevNode = lastNode.prev;

			// prevNode == null,�������ֻ��һ��Ԫ��
			if (prevNode == null) {
				unLink(null, null);
			} else {
				prevNode.next = null;
				// ����ǰ���һ�ڵ����һ�ڵ������Ͻڵ㽨���������ﵽɾ�����һ�ڵ��Ŀ��
				unLink(prevNode.prev, prevNode);
			}
			return true;

		} else {
			throw new RuntimeException(ERRMSG_NODE_NOT_FOUND);
		}
	}

	/**
	 * ɾ���ڵ�
	 * 
	 * @param e
	 */
	public boolean remove(Object o) {
		// ���������ҵ���o��ȵ�Ԫ�ز�ɾ��֮
		for (Node<E> node = f; node != null; node = node.next) {
			// �ҵ���ӦԪ�أ�ɾ��֮
			// o == null ʱnode.item == null, o != null ʱ,o.equals(node.item)
			if ((o != null && o.equals(node.item)) || (o == null && node.item == null)) {
				unLink(node.prev, node.next);
				return true;
			}
		}
		return false;
	}

	/**
	 * ɾ��ָ��λ�õĽڵ�
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
	 * ����ָ��λ�õĽڵ�
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
	 * �õ���һ���ڵ��ֵ 
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
	 * �õ����һ���ڵ��ֵ 
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
	 * ����index��ȡ�ڵ�ֵ
	 * 
	 * @param index
	 * @return
	 */
	public Object get(int index) {
		Node<E> node = getNodeByIndex(index);
		return node.item;
	}

	/**
	 * ���index�Ƿ�Ϸ�
	 * 
	 * @param index
	 * @return
	 */
	private boolean checkIndex(int index) {
		return index >= 0 && index < size;
	}

	/**
	 * �����������
	 * 
	 * @return
	 */
	public DoubleLinkedList<E> reverse() {
		DoubleLinkedList<E> linkedList = new DoubleLinkedList<>();

		// �����������η�תÿ���ڵ��pref��next
		for (Node<E> node = l; node != null; node = node.prev) {
			Node<E> newNode = node;
			linkedList.add(newNode.item);
		}
		return linkedList;
	}
	
	/**
	 * ��������
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
	 * �ڵ�
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
