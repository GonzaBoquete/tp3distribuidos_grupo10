package com.stockearte.tp3_grupo10.service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.stockearte.tp3_grupo10.model.Catalogo;
import com.stockearte.tp3_grupo10.model.Producto;
import com.stockearte.tp3_grupo10.model.Tienda;
import com.stockearte.tp3_grupo10.repository.CatalogoRepository;

@Service("catalogoService")
public class CatalogoServiceImpl implements CatalogoService {

	@Autowired
	@Qualifier("catalogoRepository")
	private CatalogoRepository catalogoRepository;

	@Autowired
	private TiendaService tiendaService;

	@Autowired
	private ProductoService productoService;

	@Override
	public Catalogo add(Long idTienda) {
		Tienda tienda = getTiendaService().getOneById(idTienda);
		if (tienda != null) {
			Catalogo catalogo = new Catalogo();
			catalogo.setTienda(tienda);
			return getCatalogoRepository().save(catalogo);
		} else {
			throw new ServiceException("No se encontro la tienda");
		}
	}

	@Override
	public Catalogo updateTienda(Long idCatalogo, Long codigoTienda) {
		Catalogo catalogo = this.getOneById(idCatalogo);
		if (catalogo != null) {
			Tienda tienda = getTiendaService().getOneById(codigoTienda);
			if (tienda != null) {
				catalogo.setTienda(tienda);
				return getCatalogoRepository().save(catalogo);
			} else {
				throw new ServiceException("No se encontro la tienda");
			}
		} else {
			throw new ServiceException("No se encontro el catalogo");
		}
	}

	@Override
	public Catalogo agregarProducto(Long codigoCatalogo, Long codigoProducto) {
		Catalogo catalogo = this.getOneById(codigoCatalogo);
		if (catalogo != null) {
			Producto producto = getProductoService().getOneById(codigoProducto);
			if (producto == null) {
				throw new ServiceException("No se encontro el producto");
			}
			catalogo.getProducto().add(producto);
			return getCatalogoRepository().save(catalogo);
		} else {
			throw new ServiceException("No se encontro el catalogo");
		}
	}

	@Override
	public Catalogo eliminarProducto(Long codigoCatalogo, Long codigoProducto) {
		Catalogo catalogo = this.getOneById(codigoCatalogo);
		if (catalogo != null) {
			Producto producto = getProductoService().getOneById(codigoProducto);
			if (producto == null) {
				throw new ServiceException("No se encontro el producto");
			}
			catalogo.getProducto().remove(producto);
			return getCatalogoRepository().save(catalogo);
		} else {
			throw new ServiceException("No se encontro el catalogo");
		}
	}

	@Override
	public void delete(Long idCatalogo) {
		Optional<Catalogo> foundCatalogo = getCatalogoRepository().findById(idCatalogo);
		if (!foundCatalogo.isEmpty()) {
			getCatalogoRepository().delete(foundCatalogo.get());
		}
	}

	@Override
	public Catalogo getOneById(Long idCatalogo) {
		Optional<Catalogo> catalogo = getCatalogoRepository().findById(idCatalogo);
		if (catalogo.isEmpty()) {
			throw new ServiceException("No se encontro el catalogo");
		}
		return catalogo.isEmpty() ? null : catalogo.get();
	}

	@Override
	public List<Catalogo> getAll() {
		return (List<Catalogo>) getCatalogoRepository().findAll();
	}

	public byte[] exportCatalogosToPdf() {
		List<Catalogo> catalogos = catalogoRepository.findAll(); // Obtiene todos los catálogos

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Document document = new Document();

		try {
			// Usa el ByteArrayOutputStream en PdfWriter
			PdfWriter.getInstance(document, baos);
			document.open();

			document.add(new Paragraph("Listado de Catálogos"));

			// Crear una tabla con 3 columnas (ID, Tienda, Productos)
			PdfPTable table = new PdfPTable(3); // 3 columnas
			table.addCell("ID");
			table.addCell("Tienda");
			table.addCell("Productos");

			// Añadir los datos de cada catálogo a la tabla
			for (Catalogo catalogo : catalogos) {
				table.addCell(catalogo.getId().toString());
				table.addCell(catalogo.getTienda().getCodigo().toString());
				table.addCell(
						catalogo.getProducto().stream().map(Producto::getNombre).collect(Collectors.joining(", ")));
			}

			document.add(table); // Añadir la tabla al documento
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			document.close();
		}

		return baos.toByteArray(); // Retorna el PDF como arreglo de bytes
	}

	public TiendaService getTiendaService() {
		return tiendaService;
	}

	public void setTiendaService(TiendaService tiendaService) {
		this.tiendaService = tiendaService;
	}

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

	public CatalogoRepository getCatalogoRepository() {
		return catalogoRepository;
	}

	public void setCatalogoRepository(CatalogoRepository catalogoRepository) {
		this.catalogoRepository = catalogoRepository;
	}

}
