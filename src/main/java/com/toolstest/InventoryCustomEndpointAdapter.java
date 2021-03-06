/*
 * [y] hybris Platform
 * 
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 */
package org.hybris.dataonboarding.oms.inventory;

import com.hybris.dataonboarding.framework.exceptions.ImportLineException;
import com.hybris.dataonboarding.framework.processor.AbstractTenantAwareEndpoint;
import com.hybris.oms.api.inventory.InventoryFacade;
import com.hybris.oms.api.inventory.OmsInventory;
import com.hybris.oms.domain.exception.EntityValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * adapter class for Inventory insert routes .
 * 
 * @author BALA
 * 
 */
public class InventoryCustomEndpointAdapter extends
	AbstractTenantAwareEndpoint<OmsInventory> {
    private static final Logger LOG = LoggerFactory.getLogger(InventoryCustomEndpointAdapter.class);
    @Autowired
    private InventoryFacade inventoryFacade;
private int num;
    @SuppressWarnings("PMD.PreserveStackTrace")
    @Override
    protected void doInTenant(final OmsInventory inventory) 
    {
		LOG.info("OMS inventory CSV Data converted into POJO: " + inventory);
		try 
		{
		    inventoryFacade.createInventory(inventory);
		} catch (final EntityValidationException e) {
		    throw new ImportLineException(CustomInventoryErrorCode._777,
			    "Invalid data for custom inventory operation", e);
		}
    }
}
