package org.sakaiproject.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.sakaiproject.util.EntityCollections.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;
import org.sakaiproject.entity.api.Entity;

public class EntityCollectionsTest {

	@Test
	public void testIsIntersectionEntityRefsToEntities() {
		Entity e1 = mock(Entity.class);
		when(e1.getReference()).thenReturn("/ref/e1");
		Entity e2 = mock(Entity.class);
		when(e2.getReference()).thenReturn("/ref/e2");
		// Good intersection.
		assertTrue(isIntersectionEntityRefsToEntities(
				Arrays.asList(new String[] { "/ref/e2" }),
				Arrays.asList(new Entity[] { e1, e2 })));
		// Empty entities
		assertFalse(isIntersectionEntityRefsToEntities(
				Arrays.asList(new String[] { "/ref/e2" }),
				Collections.emptyList()));
		// Empty references
		assertFalse(isIntersectionEntityRefsToEntities(Collections.emptyList(), Arrays.asList(new Entity[]{e1,e2})));
		// Non intersecting
		assertFalse(isIntersectionEntityRefsToEntities(Arrays.asList(new String[]{"/ref/a1", "/ref/a2"}), Arrays.asList(new Entity[]{e1,e2})));
		// Larger references
		assertTrue(isIntersectionEntityRefsToEntities(Arrays.asList(new String[]{"/ref/e1", "/ref/e2", "/ref/e3"}), Arrays.asList(new Entity[]{e1,e2})));
		// Matching
		assertTrue(isIntersectionEntityRefsToEntities(Arrays.asList(new String[]{"/ref/e1", "/ref/e2"}), Arrays.asList(new Entity[]{e1,e2})));
	}
	
	@Test
	public void testIsContainedEntityRefsToEntities() {
		Entity e1 = mock(Entity.class);
		when(e1.getReference()).thenReturn("/ref/e1");
		Entity e2 = mock(Entity.class);
		when(e2.getReference()).thenReturn("/ref/e2");
		// Good intersection.
		assertTrue(isContainedEntityRefsToEntities(
				Arrays.asList(new String[] { "/ref/e2" }),
				Arrays.asList(new Entity[] { e1, e2 })));
		// Empty entities
		assertFalse(isContainedEntityRefsToEntities(
				Arrays.asList(new String[] { "/ref/e2" }),
				Collections.emptyList()));
		// Empty references
		assertTrue(isContainedEntityRefsToEntities(Collections.emptyList(), Arrays.asList(new Entity[]{e1,e2})));
		// Non intersecting
		assertFalse(isContainedEntityRefsToEntities(Arrays.asList(new String[]{"/ref/a1", "/ref/a2"}), Arrays.asList(new Entity[]{e1,e2})));
		// Larger references
		assertFalse(isContainedEntityRefsToEntities(Arrays.asList(new String[]{"/ref/e1", "/ref/e2", "/ref/e3"}), Arrays.asList(new Entity[]{e1,e2})));
		// Matching
		assertTrue(isContainedEntityRefsToEntities(Arrays.asList(new String[]{"/ref/e1", "/ref/e2"}), Arrays.asList(new Entity[]{e1,e2})));
	}

	@Test
	public void testIsEqualEntityRefsToEntities() {
		Entity e1 = mock(Entity.class);
		when(e1.getReference()).thenReturn("/ref/e1");
		Entity e2 = mock(Entity.class);
		when(e2.getReference()).thenReturn("/ref/e2");
		//Intersection.
		assertFalse(isEqualEntityRefsToEntities(
				Arrays.asList(new String[] { "/ref/e2" }),
				Arrays.asList(new Entity[] { e1, e2 })));
		// Empty entities
		assertFalse(isEqualEntityRefsToEntities(
				Arrays.asList(new String[] { "/ref/e2" }),
				Collections.emptyList()));
		// Empty references
		assertFalse(isEqualEntityRefsToEntities(Collections.emptyList(), Arrays.asList(new Entity[]{e1,e2})));
		// Non intersecting
		assertFalse(isEqualEntityRefsToEntities(Arrays.asList(new String[]{"/ref/a1", "/ref/a2"}), Arrays.asList(new Entity[]{e1,e2})));
		// Larger references
		assertFalse(isEqualEntityRefsToEntities(Arrays.asList(new String[]{"/ref/e1", "/ref/e2", "/ref/e3"}), Arrays.asList(new Entity[]{e1,e2})));
		// Matching
		assertTrue(isEqualEntityRefsToEntities(Arrays.asList(new String[]{"/ref/e1", "/ref/e2"}), Arrays.asList(new Entity[]{e1,e2})));
		// Matching, but different order
		assertTrue(isEqualEntityRefsToEntities(Arrays.asList(new String[]{"/ref/e2", "/ref/e1"}), Arrays.asList(new Entity[]{e1,e2})));
		// Same ref twice
		assertFalse(isEqualEntityRefsToEntities(Arrays.asList(new String[]{"/ref/e1", "/ref/e1"}), Arrays.asList(new Entity[]{e1,e2})));
		// Same entity twice
		assertFalse(isEqualEntityRefsToEntities(Arrays.asList(new String[]{"/ref/e1", "/ref/e2"}), Arrays.asList(new Entity[]{e1,e1})));
	}

	@Test
	public void testEntityCollectionContainsRefString() {
		Entity e1 = mock(Entity.class);
		when(e1.getReference()).thenReturn("/ref/e1");
		Entity e2 = mock(Entity.class);
		when(e2.getReference()).thenReturn("/ref/e2");
		// Good
		assertTrue(entityCollectionContainsRefString(Arrays.asList(new Entity[]{e1,e2}), "/ref/e1"));
		// Bad
		assertFalse(entityCollectionContainsRefString(Arrays.asList(new Entity[]{e1,e2}), "/ref/missing"));
		// Empty entities
		assertFalse(entityCollectionContainsRefString(Collections.emptyList(), "/ref/e1"));
	}

	@Test
	public void testRefCollectionContainsEntity() {
		Entity e1 = mock(Entity.class);
		when(e1.getReference()).thenReturn("/ref/e1");
		Entity e2 = mock(Entity.class);
		when(e2.getReference()).thenReturn("/ref/e2");
		// Good
		assertTrue(refCollectionContainsEntity(Arrays.asList(new String[]{"/ref/e1", "/ref/e2"}), e1));
		// Bad
		assertFalse(refCollectionContainsEntity(Arrays.asList(new String[]{"/ref/a1", "/ref/a2"}), e1));
		// Empty references
		assertFalse(refCollectionContainsEntity(Collections.emptyList(), e1));
	}

	@Test
	public void testSetEntityRefsFromEntities() {
		Entity e1 = mock(Entity.class);
		when(e1.getReference()).thenReturn("/ref/e1");
		Entity e2 = mock(Entity.class);
		when(e2.getReference()).thenReturn("/ref/e2");
		
		ArrayList<String> refernces = new ArrayList<String>();
		setEntityRefsFromEntities(refernces, Arrays.asList(new Entity[]{e1,e2}));
		assertTrue(refernces.contains("/ref/e1"));
		assertTrue(refernces.contains("/ref/e2"));
	}

	// @Test
	// Why......
	public void testComputeAddedRemovedEntityRefsFromNewEntitiesOldRefs() {
		
	}
	
}
