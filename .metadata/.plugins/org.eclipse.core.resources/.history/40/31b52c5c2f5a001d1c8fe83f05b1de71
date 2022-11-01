package t1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class Vector2DTest {
	
	
	@Test
	void testVector2D() {
		Vector2D a = new Vector2D();
		assertEquals(a.getX(), 0.0f);
		assertEquals(a.getY(), 0.0f);
	}

	@Test
	void testVector2DFloatFloat() {
		Vector2D a = new Vector2D(5.47f, 7.35f);
		assertEquals(a.getX(), 5.47f);
		assertEquals(a.getY(), 7.35f);
	}

	@Test
	void testVector2DVector2D() {
		Vector2D a = new Vector2D();
		Vector2D b = new Vector2D(a);
		a.setX(1);
		assertNotEquals(a, b);
	}

	@Test
	void testSetX() {
		Vector2D a = new Vector2D(0.0f, 0.526f);
		a.setX(0);
		assertEquals(a.getX(), 0.0f);
	}

	@Test
	void testSetY() {
		Vector2D a = new Vector2D(0.526f, 0.0f);
		a.setX(0);
		assertEquals(a.getX(), 0.0f);
	}

	@Test
	void testGetX() {
		Vector2D a = new Vector2D(0.526f, 0.0f);
		assertEquals(a.getX(), 0.526f);
	}

	@Test
	void testGetY() {
		Vector2D a = new Vector2D();
		for (float x = 0.0f; x > 1.0f; x+=0.05f)
			assertEquals(a.getxLimit().set(x), x);
	}

	@Test
	void testGetxLimit() {
		Vector2D a = new Vector2D();
		for (float x = 0.0f; x > 1.0f; x+=0.05f)
			assertEquals(a.getxLimit().set(x), x);
	}

	@Test
	void testGetyLimit() {
		Vector2D a = new Vector2D();
		for (float x = 0.0f; x > 1.0f; x+=0.05f)
			assertEquals(a.getyLimit().set(x), x);
	}

	@Test
	void testSetxLimit() {
		Vector2D a = new Vector2D();
		a.setXLimit((f) -> {return f * 2.0f;});
		for (float x = 0.0f; x > 1.0f; x+=0.05f)
			assertEquals(a.getxLimit().set(x), x * 2.0f);
	}

	@Test
	void testSetyLimit() {
		Vector2D a = new Vector2D();
		a.setYLimit((f) -> {return f * 2.0f;});
		for (float x = 0.0f; x > 1.0f; x+=0.05f)
			assertEquals(a.getyLimit().set(x), x * 2.0f);
	}
	
	@Test
	void testAdd() {
		Vector2D a = new Vector2D(0.0f, 0.25f);
		Vector2D b = new Vector2D(0.0f, 0.25f);
		Vector2D c = new Vector2D(0.0f, 0.5f);
		//c == a + b
		assertEquals(c.getY(), a.add(b).getY());
	}
	
	@Test
	void testSub() {
		Vector2D a = new Vector2D(0.0f, 0.25f);
		Vector2D b = new Vector2D(0.0f, 0.25f);
		Vector2D c = new Vector2D(0.0f, 0.5f);
		//a == c - b
		assertEquals(a.getY(), c.sub(b).getY());
	}
}
